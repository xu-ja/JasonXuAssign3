package jason.xu.s991545529.ui.jasonfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import jason.xu.s991545529.R;

public class JasonFragment extends Fragment {

    private CanvasView canvas;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
/*        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);*/
        View root = inflater.inflate(R.layout.fragment_jason, container, false);
        final TextView textView = root.findViewById(R.id.jasonTextHome);
/*        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        canvas = (CanvasView) root.findViewById(R.id.jasonCanvasView);

        Button clearButton = (Button) root.findViewById(R.id.jasonButtonClear);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas.clearCanvas();
            }
        });
        return root;
    }
}