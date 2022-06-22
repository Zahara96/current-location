package com.example.location_access;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button location_button;
    //TextView textView, textView2, textView3, textView4, textView5;
    FusedLocationProviderClient fusedLocationProviderClient;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        location_button = findViewById(R.id.location_button);
        //textView = findViewById(R.id.textView);
        //textView2 = findViewById(R.id.textView2);
        //textView3 = findViewById(R.id.textView3);
        //textView4 = findViewById(R.id.textView4);
        //textView5 = findViewById(R.id.textView5);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        location_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });

    }


    @SuppressLint("MissingPermission")
    private void getLocation() {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();

                if (location != null) {
                    Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());

                    try {
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                        //textView.setText(Html.fromHtml("<font color = '#6200EE'><b> Latitude: </b><br></font>" + addresses.get(0).getLatitude()));
                        //textView2.setText(Html.fromHtml("<font color = '#6200EE'><b> Longitude: </b><br></font>" + addresses.get(0).getLongitude()));
                        //textView3.setText(Html.fromHtml("<font color = '#6200EE'><b> Country Name: </b><br></font>" + addresses.get(0).getCountryName()));
                        //textView4.setText(Html.fromHtml("<font color = '#6200EE'><b> Locality: </b><br></font>" + addresses.get(0).getLocality()));
                        //textView5.setText(Html.fromHtml("<font color = '#6200EE'><b> Address: </b><br></font>" + addresses.get(0).getAddressLine(0)));

                        Intent locationIntent = new Intent(MainActivity.this,MainActivity2.class);
                        locationIntent.putExtra("Latitude",addresses.get(0).getLatitude());
                        locationIntent.putExtra("Longitude",addresses.get(0).getLongitude());
                        locationIntent.putExtra("CountryName",addresses.get(0).getCountryName());
                        locationIntent.putExtra("Locality",addresses.get(0).getLocality());
                        locationIntent.putExtra("AddressLine",addresses.get(0).getAddressLine(0));
                        //locationIntent.putExtra("addresses", (Serializable) addresses);
                        startService(locationIntent);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            }
        });

    }


}