package edu.neu.numad21su_jefftaylor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class Locator extends AppCompatActivity implements LocationListener {

    TextView latitudeDisplay;
    TextView longitudeDisplay;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locator);

        latitudeDisplay = findViewById(R.id.latitude);
        longitudeDisplay = findViewById(R.id.longitude);

        if (ContextCompat.checkSelfPermission(Locator.this, Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Locator.this, new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION }, 100);

        }

        getLocation();
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {

        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 5, Locator.this);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        latitudeDisplay.setText(String.valueOf(location.getLatitude()));
        longitudeDisplay.setText(String.valueOf(location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }
}