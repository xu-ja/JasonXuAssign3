package jason.xu.s991545529.ui.s991545529fragment;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.PathInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import jason.xu.s991545529.R;

public class S991545529Fragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_s991545529, container, false);

        // Path for moving Moon ImageView in a circle around the Earth ImageView
        Path path = new Path();
        path.arcTo(200f, 700f, 1000f, 1400f, 270f, -359.99f, true);
        ObjectAnimator animator = ObjectAnimator.ofFloat(root.findViewById(R.id.jasonImageViewMoon), root.X, root.Y, path);
        animator.setDuration(5000);

        // Button to start Moon rotation animation
        Button startButton = (Button) root.findViewById(R.id.jasonButtonStartMoon);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animator.start();
            }
        });

        // Button to stop Moon rotation animation
        Button stopButton = (Button) root.findViewById(R.id.jasonButtonStopMoon);
        stopButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animator.cancel();
            }
        }));
        return root;
    }
}