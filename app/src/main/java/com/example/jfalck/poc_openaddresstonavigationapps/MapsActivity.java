package com.example.jfalck.poc_openaddresstonavigationapps;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        initViews();
    }

    private void initViews() {
        mButton = findViewById(R.id.main_activity_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Geocoder geocoder = new Geocoder(MapsActivity.this);
        List<Address> addresses;
        double latitude = 0;
        double longitude = 0;
        try {
            addresses = geocoder.getFromLocationName("1 place Giovanni da verrazanno, 69009 Lyon, France", 1);
            if (addresses.size() > 0) {
                latitude = addresses.get(0).getLatitude();
                longitude = addresses.get(0).getLongitude();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add a marker in Sydney and move the camera
        if (!(latitude == 0 && longitude == 0)) {
            setMap(latitude, longitude);
        }
    }

    private void setMap(final double latitude, final double longitude) {
        LatLng sydney = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(14));
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                goToApp(latitude, longitude);
            }
        });
        mMap.getUiSettings().setAllGesturesEnabled(false);
    }

    private void goToApp(double latitude, double longitude) {
        String uri = "geo: " + latitude + "," + longitude + "?q=" + latitude + "," + longitude;
        startActivity(new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse(uri)));
    }
}
