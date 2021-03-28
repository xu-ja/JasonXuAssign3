package jason.xu.s991545529.ui.jasonfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import jason.xu.s991545529.R;

public class JasonFragment extends Fragment {

    private CanvasView canvas;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_jason, container, false);
        //final TextView textView = root.findViewById(R.id.jasonTextHome);

        canvas = (CanvasView) root.findViewById(R.id.jasonCanvasView);

        Button clearButton = (Button) root.findViewById(R.id.jasonButtonClear);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas.clearCanvas();
            }
        });

        RadioGroup colours = (RadioGroup) root.findViewById(R.id.jasonRadioGroupColours);
        RadioGroup thickness = (RadioGroup) root.findViewById(R.id.jasonRadioGroupThickness);
        Button updatePenButton = (Button) root.findViewById(R.id.jasonButtonUpdatePen);
        updatePenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedColourId = colours.getCheckedRadioButtonId();
                RadioButton colour = (RadioButton) root.findViewById(selectedColourId);
                int selectedThicknessId = thickness.getCheckedRadioButtonId();
                RadioButton howThick = (RadioButton) root.findViewById(selectedThicknessId);
                canvas.updatePen(colour.getText(), howThick.getText());
            }
        });

        return root;
    }
}