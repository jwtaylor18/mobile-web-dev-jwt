package edu.neu.numad21su_jefftaylor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class AtYourService extends AppCompatActivity {

    private Handler textHandler = new Handler();
    private TextView statusText;
    private TextView enterNumber;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_at_your_service);
        statusText = findViewById(R.id.runStatusText);
        enterNumber = findViewById(R.id.editTextNumber);
        progressBar = findViewById(R.id.progressBar);
    }

    public void runOnRunnableThread(View view) {

        if (validateNumberEntered()) {
            RunnableThread runnableThread = new RunnableThread();
            new Thread(runnableThread).start();
        }
        else {
            statusText.setText("Please enter a number 1-10");
        }
    }

    class RunnableThread implements Runnable {

        @Override
        public void run() {

            JSONObject jObject = new JSONObject();
            try {

                String url_initial = "https://www.boredapi.com/api/activity?participants=" + enterNumber.getText().toString();

                URL url = new URL(url_initial);

                textHandler.post(() -> {
                    progressBar.setVisibility(ProgressBar.VISIBLE);
                });

                try {
                    Thread.sleep(2000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Get String response from the url address
                String resp = NetworkUtil.httpResponse(url);

                // Transform String into JSONObject
                jObject = new JSONObject(resp);

                textHandler.post(() -> {
                    progressBar.setVisibility(ProgressBar.INVISIBLE);
                });

                displayResults(jObject);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean validateNumberEntered() {

        if (enterNumber.getText().toString().equals("")) {
            return false;
        }
        else {
            int numEntered = Integer.parseInt(enterNumber.getText().toString());
            return numEntered >= 1 && numEntered <= 10;
        }
    }

    private void displayResults(JSONObject jsonObject) {

        textHandler.post(() -> {
            try {
                statusText.setText(jsonObject.getString("activity"));
            }
            catch (JSONException e) {
                statusText.setText("No luck with this number - try a different number please!");
            };
        });
    }
}