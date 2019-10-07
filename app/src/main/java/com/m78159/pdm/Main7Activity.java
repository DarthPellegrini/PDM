package com.m78159.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main7Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
    }

    public void aula7_1Click(View view) {
        Intent intent = new Intent(getApplicationContext(),Main7_1Activity.class);
        startActivity(intent);

    }

    public void aula7_2Click(View view) {
        Intent intent = new Intent(getApplicationContext(),Main7_2Activity.class);
        startActivity(intent);

    }
}
