package com.m78159.pdm;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {

    final String TAG = "App";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //A activity está sendo criada
        Log.d(TAG, "onCreate");

    }

    @Override //A activity está prestes a ser visível
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override //A activity está visível
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override //A activity está voltando a receber o foco depois de estar pausada
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override // outra activity tem o foco, esta activity será pausada
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override //A activity não está mais visível, mas permanece em memória
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override //A activity está prestes a ser destruída (removida da memória)
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}
