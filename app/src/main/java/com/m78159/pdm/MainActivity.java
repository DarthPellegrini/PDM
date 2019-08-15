package com.m78159.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void aula1Click(View view) {
        Intent intent = new Intent(getApplicationContext(),Main1Activity.class);
        startActivity(intent);

    }

    public void aula2Click(View view) {
        Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
        startActivity(intent);

    }
}
