package com.m78159.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {

    final String TAG = "CICLO";

    private EditText tempC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tempC = findViewById(R.id.txtTempC);

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

    public void calcClick(View view) {
        String result = String.valueOf(( Double.parseDouble(tempC.getText().toString()) * 9/5) + 32);
        Toast.makeText(this, result,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Main2_1Activity.class);
        intent.putExtra("resultado",result);
        startActivity(intent);
    }

}
