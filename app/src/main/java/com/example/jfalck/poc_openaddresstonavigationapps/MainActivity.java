package com.example.jfalck.poc_openaddresstonavigationapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView mStaticMapImage;
    private String mapsUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMapsUrl();
        initViews();

    }

    private void initMapsUrl() {
        String latEiffelTower = "48.858235";
        String lngEiffelTower = "2.294571";
        mapsUrl = "http://maps.google.com/maps/api/staticmap?center=" + latEiffelTower + "," + lngEiffelTower + "&zoom=15&size=200x200&sensor=false";
    }

    private void initViews() {
        mStaticMapImage = findViewById(R.id.map_static_image);
        mStaticMapImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
