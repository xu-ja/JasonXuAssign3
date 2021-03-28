package jason.xu.s991545529.ui.s991545529fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
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

        TextView firstName = (TextView) root.findViewById(R.id.jasonTextViewFirstName);

        TextView lastName = (TextView) root.findViewById(R.id.jasonTextViewLastName);

        return root;
    }
}