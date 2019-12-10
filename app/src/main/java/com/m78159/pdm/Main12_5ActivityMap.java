package com.m78159.pdm;

import androidx.fragment.app.FragmentActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Main12_5ActivityMap extends FragmentActivity implements OnMapReadyCallback {

    private Main12_1ActivityDatabaseHelper helper = new Main12_1ActivityDatabaseHelper(this);
    private String query = "";
    private Cursor cursor;
    private SQLiteDatabase db;
    private OutputStream outStream = null;
    private ByteArrayOutputStream byteOutStream = null;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main12_5_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                query = "SELECT * FROM trabalho_audio";

                db = helper.getReadableDatabase();
                cursor = db.rawQuery(query,null);

                cursor.moveToFirst();

                if(cursor.isFirst()) {

                    do {
                        LatLng latlng = new LatLng(Double.parseDouble(cursor.getString(cursor.getColumnIndex("latitude"))),
                                                   Double.parseDouble(cursor.getString(cursor.getColumnIndex("longitude"))));
                        googleMap.addMarker(new MarkerOptions().position(latlng).title(cursor.getString(1)));
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
                    } while (cursor.moveToNext());

                    cursor.close();

                }

                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        helper = new Main12_1ActivityDatabaseHelper(getApplicationContext());

                        db = helper.getReadableDatabase();

                        cursor = db.rawQuery("SELECT audio FROM trabalho_audio " +
                                        "WHERE latitude = ? AND longitude = ?",
                                new String[]{String.valueOf(marker.getPosition().latitude), String.valueOf(marker.getPosition().longitude)});

                        cursor.moveToFirst();

                        try {
                            outStream = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath() + "/audio.3gp");
                            byteOutStream = new ByteArrayOutputStream();
                            byteOutStream.write(cursor.getBlob(0));
                            byteOutStream.writeTo(outStream);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                outStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        setVolumeControlStream(AudioManager.STREAM_MUSIC);
                        mediaPlayer = new MediaPlayer();

                        try {
                            mediaPlayer.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath() + "/audio.3gp");
                            mediaPlayer.prepare();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        mediaPlayer = MediaPlayer.create(getApplicationContext(), Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath() + "/audio.3gp"));

                        mediaPlayer.start();

                        if (!marker.isInfoWindowShown())
                            marker.showInfoWindow();

                        Toast.makeText(getApplicationContext(), "Reproduzindo aúdio...", Toast.LENGTH_LONG).show();

                        return true;
                    }
                });
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        query = "SELECT * FROM trabalho_audio";

        SQLiteDatabase db = helper.getReadableDatabase();
        cursor = db.rawQuery(query,null);

        cursor.moveToFirst();

        if(cursor.isFirst()) {
            do {
                LatLng latlng = new LatLng(Double.parseDouble(cursor.getString(cursor.getColumnIndex("latitude"))),
                        Double.parseDouble(cursor.getString(cursor.getColumnIndex("longitude"))));
                googleMap.addMarker(new MarkerOptions().position(latlng).title(cursor.getString(cursor.getColumnIndex("title"))));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
            } while (cursor.moveToNext());
            cursor.close();
        }
    }

    @Override
    protected void onDestroy(){
        helper.close();
        super.onDestroy();
    }

}
