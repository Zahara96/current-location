package com.example.location_access;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView textView, textView2, textView3, textView4, textView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView = findViewById(R.id.textView_1);
        textView2 = findViewById(R.id.textView_2);
        textView3 = findViewById(R.id.textView_3);
        textView4 = findViewById(R.id.textView_4);
        textView5 = findViewById(R.id.textView_5);

        Intent locationGetIntent = getIntent();
        String Latitude = locationGetIntent.getStringExtra("Latitude");
        String Longitude = locationGetIntent.getStringExtra("Longitude");
        String CountryName = locationGetIntent.getStringExtra("CountryName");
        String Locality = locationGetIntent.getStringExtra("Locality");
        String AddressLine = locationGetIntent.getStringExtra("AddressLine");

        textView.setText(Html.fromHtml("<font color = '#DBE85E'><b> Latitude: </b><br></font>" + Latitude));
        textView2.setText(Html.fromHtml("<font color = '#DBE85E'><b> Longitude: </b><br></font>" + Longitude));
        textView3.setText(Html.fromHtml("<font color = '#DBE85E'><b> Country Name: </b><br></font>" + CountryName));
        textView4.setText(Html.fromHtml("<font color = '#DBE85E'><b> Locality: </b><br></font>" + Locality));
        textView5.setText(Html.fromHtml("<font color = '#DBE85E'><b> Address: </b><br></font>" + AddressLine));




    }
}