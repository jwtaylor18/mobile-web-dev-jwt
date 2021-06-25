package edu.neu.numad21su_jefftaylor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class AtYourService extends AppCompatActivity {

    private Handler textHandler = new Handler();
    TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_at_your_service);
        statusText = findViewById(R.id.runStatusText);
    }

    public void runOnRunnableThread(View view) {
        RunnableThread runnableThread = new RunnableThread();
        new Thread(runnableThread).start();
    }

    class RunnableThread implements Runnable {

        @Override
        public void run() {
            for (int i = 1; i <= 10; i++) {
                final int finalI = i;

                textHandler.post(() -> {
                    statusText.setText("New thread (Runnable): " + finalI);
                    if (finalI == 10) {
                        statusText.setText("");
                    }
                });

                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}