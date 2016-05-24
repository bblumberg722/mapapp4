package com.example.pearlleff.mapapp4;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        List<Marker> markers = new ArrayList<Marker>(4);
        List<LatLng> locations = new ArrayList<LatLng>(4);

        locations.add(new LatLng(40.7687, -73.9647)); // sydney
        locations.add(new LatLng(40.7422, -73.9907)); // greece

        LatLng hi = new LatLng(0,0);

        markers.add(mMap.addMarker(new MarkerOptions().position(locations.get(1))
                .title("Hunter")));
        markers.add(mMap.addMarker(new MarkerOptions().position(locations.get(0)).title("Touro")));

        // add: show an info bar (showInfoBar()) if user sets that in settings. Can only show one
        // at a time though.

        // add: let user choose map type.

        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Marker marker : markers) {
            builder.include(marker.getPosition());
        }
        LatLngBounds bounds = builder.build();

        int padding = 100; // offset from edges of the map in pixels

        final CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        /*
        mMap.animateCamera(cu);
        */
        mMap .setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
               @Override public void onMapLoaded()
               {
                   mMap.moveCamera(cu);
               }
           });
    }
}
