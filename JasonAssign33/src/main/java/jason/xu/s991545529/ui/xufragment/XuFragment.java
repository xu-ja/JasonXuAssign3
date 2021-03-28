package jason.xu.s991545529.ui.xufragment;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

import jason.xu.s991545529.R;

public class XuFragment extends Fragment {

    private static final int PERMISSION_REQUEST_CODE = 123;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
/*        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);*/
        root = inflater.inflate(R.layout.fragment_xu, container, false);
/*        final TextView textView = root.findViewById(R.id.jasonTextDashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        Button cameraButton = (Button) root.findViewById(R.id.jasonButtonService);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(root.getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivity(cameraIntent);
                } else {
                    requestPermission();
                }
            }
        });
        return root;
    }

    private void requestPermission() {
        requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(root.getContext())
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivity(cameraIntent);
                } else {
                    Snackbar permissionDenied = Snackbar.make(root, getResources().getString(R.string.permission_denied), Snackbar.LENGTH_LONG);
                    permissionDenied.show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(root.getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            showMessageOKCancel("You need to allow access permissions",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermission();
                                            }
                                        }
                                    });
                        }
                    }
                    return;
                }
        }
    }


}