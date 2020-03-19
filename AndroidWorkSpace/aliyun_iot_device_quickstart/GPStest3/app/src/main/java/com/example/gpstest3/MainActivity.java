package com.example.gpstest3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PermissionGroupInfo;
import android.location.Criteria;
import android.location.LocationProvider;
import android.os.Build;
import android.os.Bundle;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView tvLocation;
    private TextView tvAddress;
    private Button button;

    String Tag = "MainActivity";

    LocationUtils locationUtils = new LocationUtils();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
        locationUtils.getLocations(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("sad",locationUtils.getLocations(MainActivity.this));

            }
        });
    }

    private void initUi() {
        button = (Button)findViewById(R.id.button1) ;
        tvLocation = (TextView) findViewById(R.id.tv_location);
        tvAddress = (TextView) findViewById(R.id.tv_address);

    }
}



