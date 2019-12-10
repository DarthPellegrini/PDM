package com.m78159.pdm;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;

public class Main12Activity extends AppCompatActivity {

    private Main12_1ActivityDatabaseHelper helper;
    private SQLiteDatabase db;
    private ImageButton btnStartRecording;
    private Button btnStopRecording;
    private EditText audioTitle;
    private Double lat, lon;
    private String PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/audio.3gp";
    private MediaRecorder mediaRecorder;
    private FileInputStream fileInputStream;
    private ByteArrayOutputStream baos;
    private byte[] buffer;
    private byte[] fileByteArray;
    private int read;
    private ContentValues values;
    private String completeDate;
    
    
    private FusedLocationProviderClient mFusedLocationClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        btnStartRecording = findViewById(R.id.btnStartRecording);

        btnStopRecording = findViewById(R.id.btnStopRecording);
        btnStopRecording.setEnabled(false);

        audioTitle = findViewById(R.id.audioTitle);

        helper = new Main12_1ActivityDatabaseHelper(getApplicationContext());

        btnStartRecording.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if(checkPermissions()) {

                    getLastLocation();

                    if(!isLocationEnabled())
                        Toast.makeText(getApplicationContext(), "Ative a Localização!", Toast.LENGTH_SHORT).show();

                    completeDate = LocalDateTime.now().toString();

                    mediaRecorderReady();

                    try {
                        mediaRecorder.prepare();
                        mediaRecorder.start();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    btnStartRecording.setEnabled(false);
                    btnStopRecording.setEnabled(true);

                    Toast.makeText(getApplicationContext(), "Gravação Iniciada", Toast.LENGTH_LONG).show();

                } else {
                    requestPermissions();
                }
            }
        });

        btnStopRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaRecorder.stop();
                btnStopRecording.setEnabled(false);
                btnStartRecording.setEnabled(true);


                try {
                    fileInputStream = new FileInputStream(PATH);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                baos = new ByteArrayOutputStream();
                buffer = new byte[1024];
                read = 0;

                try {
                    while ((read = fileInputStream.read(buffer)) != -1) {
                        baos.write(buffer, 0, read);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    baos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                fileByteArray = baos.toByteArray();

                db = helper.getWritableDatabase();

                values = new ContentValues();
                values.put("title", audioTitle.getText().toString());
                values.put("audio", fileByteArray);
                values.put("timeOfRecording", completeDate);
                values.put("latitude", String.valueOf(lat));
                values.put("longitude", String.valueOf(lon));

                long resultado = db.insert("trabalho_audio", null, values);
                if(resultado != -1) {
                    Toast.makeText(getApplicationContext(), "Gravação Salva com Sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Erro ao Salvar!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void mediaRecorderReady(){
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(PATH);
    }

    private boolean isLocationEnabled(){
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        assert locationManager != null;
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    private boolean checkPermissions() {
        return (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED);
    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            lat = mLastLocation.getLatitude();
            lon = mLastLocation.getLongitude();
        }
    };

    @SuppressLint("MissingPermission")
    private void requestNewLocationData(){

        @SuppressLint("RestrictedApi") LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );

    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                             Manifest.permission.ACCESS_FINE_LOCATION,
                             Manifest.permission.WRITE_EXTERNAL_STORAGE,
                             Manifest.permission.RECORD_AUDIO},24);
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation(){
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                Location location = task.getResult();
                                if (location == null) {
                                    requestNewLocationData();
                                } else {
                                    lat = location.getLatitude();
                                    lon = location.getLongitude();
                                }
                            }
                        }
                );
            } else {
                Toast.makeText(this, "Habilite o GPS!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }

    public void onClickListActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), Main12_4ActivityList.class);
        startActivity(intent);
    }

    public void onClickMapActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), Main12_5ActivityMap.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy(){
        helper.close();
        super.onDestroy();
    }

}
