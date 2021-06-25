package edu.neu.numad21su_jefftaylor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showInfo(View view) {
        TextView textView = findViewById(R.id.info);
        textView.setText("Jeff Taylor taylor.je@northeastern.edu");
    }

    public void openClickyClicky(View view) {
        Intent intent = new Intent(this, ClickyClicky.class);
        startActivity(intent);
    }

    public void openLinkCollector(View view) {
        Intent intent = new Intent(this, LinkCollector.class);
        startActivity(intent);
    }

    public void openLocator(View view) {
        Intent intent = new Intent(this, Locator.class);
        startActivity(intent);
    }

    public void openAtYourService(View view) {
        Intent intent = new Intent(this, AtYourService.class);
        startActivity(intent);
    }


}