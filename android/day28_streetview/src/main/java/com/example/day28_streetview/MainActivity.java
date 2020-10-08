package com.example.day28_streetview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openGoogleMaps(View view){
        String geoURI = "geo:55.001379,73.288621?z=20";
        Uri geo = Uri.parse(geoURI);
        Intent geoIntent = new Intent(Intent.ACTION_VIEW, geo);
        startActivity(geoIntent);
    }

    public void openStreetView(View view) {
        String geoUriString = "google.streetview:cbll=59.939448,30.328264&cbp=1,99.56,,1,2.0&mz=19";
        Uri geoUri = Uri.parse(geoUriString);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, geoUri);
        startActivity(mapIntent);
    }
}
