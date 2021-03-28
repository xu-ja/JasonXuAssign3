package jason.xu.s991545529.ui.xufragment;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    AnimationDrawable mframeAnimation;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_xu, container, false);

        Button cameraButton = (Button) root.findViewById(R.id.jasonButtonService);
        // Camera button to launch camera app
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If camera permission is granted, open camera
                if (ContextCompat.checkSelfPermission(root.getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivity(cameraIntent);
                } else {
                    // If camera permission is not granted, then request for permission
                    requestPermission();
                }
            }
        });

        RadioGroup animationSpeeds = (RadioGroup) root.findViewById(R.id.jasonRadioGroupAnimationSpeed);
        Button animationButton = (Button) root.findViewById(R.id.jasonButtonStartAnimation);
        // Button to check for animation speed selected in radio group
        animationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedSpeedId = animationSpeeds.getCheckedRadioButtonId();
                RadioButton speed = (RadioButton) root.findViewById(selectedSpeedId);
                int duration = animationSpeed(speed.getText());
                startAnimation(duration);
            }
        });
        return root;
    }

    private int animationSpeed(CharSequence speed) {
        if (speed.equals(root.getContext().getString(R.string.speed1))) {
            return 300;
        } else if (speed.equals(root.getContext().getString(R.string.speed2))) {
            return 400;
        } else if (speed.equals(root.getContext().getString(R.string.speed3))) {
            return 500;
        } else if (speed.equals(root.getContext().getString(R.string.speed4))) {
            return 600;
        } else {
            return 300;
        }
    }

    private void startAnimation(int duration) {
        // Sets animation through 5 random images.
        ImageView img = (ImageView) root.findViewById(R.id.jasonImageViewAnimation);

        BitmapDrawable frame1 = (BitmapDrawable) root.getContext().getDrawable(R.drawable.img1);
        BitmapDrawable frame2 = (BitmapDrawable) root.getContext().getDrawable(R.drawable.img2);
        BitmapDrawable frame3 = (BitmapDrawable) root.getContext().getDrawable(R.drawable.img3);
        BitmapDrawable frame4 = (BitmapDrawable) root.getContext().getDrawable(R.drawable.img4);
        BitmapDrawable frame5 = (BitmapDrawable) root.getContext().getDrawable(R.drawable.img5);

        mframeAnimation = new AnimationDrawable();
        mframeAnimation.setOneShot(false);
        mframeAnimation.addFrame(frame1, duration);
        mframeAnimation.addFrame(frame2, duration);
        mframeAnimation.addFrame(frame3, duration);
        mframeAnimation.addFrame(frame4, duration);
        mframeAnimation.addFrame(frame5, duration);

        img.setBackground(mframeAnimation);
        mframeAnimation.setVisible(true,true);
        mframeAnimation.start();
    }

    private void requestPermission() {
        // Request for camera permission
        requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);
    }

    // Alert dialog for confirming camera permission
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
                // Open camera if permission is granted
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivity(cameraIntent);
                } else {
                    // Display snackbar if camera permission is denied
                    Snackbar permissionDenied = Snackbar.make(root, getResources().getString(R.string.permission_denied), Snackbar.LENGTH_LONG);
                    permissionDenied.show();

                    // Request for camera permission
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