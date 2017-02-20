package com.example.pc02.watshappening;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;

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
        final String LATITUD = "latitud";
        final String LONGITUD = "longitud";
        final String NOMBRE = "nombre";
        String id="";
        String nombre="";
        double latitud=0, longitud=0;

        SharedPreferences sharedPreferences = this.getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("id", "0");


        BBDDLocal bdlocal = new BBDDLocal(this.getApplicationContext(), Environment.getExternalStorageDirectory() + File.separator + "eventop.db", null, 2);
        SQLiteDatabase db = bdlocal.getReadableDatabase();

        String[] campos = new String[] {NOMBRE, LATITUD, LONGITUD};
        String[] args = new String[]{id};

        Cursor c = db.query("acontecimientos", campos, "id=?", args , null, null, null);

        if(c.moveToFirst()){
            latitud= c.getDouble(c.getColumnIndex(LATITUD));
            longitud = c.getDouble(c.getColumnIndex(LONGITUD));
            nombre = c.getString(c.getColumnIndex(NOMBRE));

            if(latitud!=0&&longitud!=0){
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(latitud, longitud))
                        .title(nombre));
            }
        }

        googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(38.149528, -4.789756))
                    .title(nombre));

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(40.916394, -5.765756))
                .title(nombre));


        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(41.546286, -2.020875))
                .title(nombre));




    }
}
