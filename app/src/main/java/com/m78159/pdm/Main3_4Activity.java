package com.m78159.pdm;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Main3_4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3_4);

        getSupportActionBar().setTitle(R.string.app_3_4_title);
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#999999")));
    }

}
