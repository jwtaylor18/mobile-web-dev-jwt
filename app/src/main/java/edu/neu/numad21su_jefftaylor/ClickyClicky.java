package edu.neu.numad21su_jefftaylor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ClickyClicky extends AppCompatActivity {

    private TextView pressedTextView;
    private String pressedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicky_clicky);
        pressedTextView = findViewById(R.id.textView2);
    }

    public void showPressedButton(View view) {
        Button b = (Button) view;
        pressedText = "Pressed: " + b.getText().toString();
        pressedTextView.setText(pressedText);

    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putString("pressedButton",);
//    }


}