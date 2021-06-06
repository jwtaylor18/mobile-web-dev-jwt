package edu.neu.numad21su_jefftaylor;


import android.content.Intent;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class ItemCard extends AppCompatActivity implements ItemClickListener {

    private final String urlName;
    private final String url;

    //Constructor
    public ItemCard(String urlName, String url) {
        this.urlName = urlName;
        this.url = url;
    }

    //Getters
    public String getUrlName() {
        return urlName;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public void onItemClick(int position, View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(url));
        (v.getContext()).startActivity(intent);
  }

}