package com.m78159.pdm;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Main3_3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3_3);

        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.quiz_transito_icon_shrank);
        getSupportActionBar().setTitle(R.string.app_3_3_title);
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#1e1e1e")));

    }

}
