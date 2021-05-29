package edu.neu.numad21su_jefftaylor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ClickyClicky extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicky_clicky);
    }

    public void showPressedButton(View view) {
        Button b = (Button) view;
        TextView textView = findViewById(R.id.textView2);
        textView.setText("Pressed: " + b.getText().toString());
    }
}