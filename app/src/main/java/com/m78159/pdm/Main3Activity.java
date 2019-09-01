package com.m78159.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

    }

    public void aula3_1Click(View view) {
        Intent intent = new Intent(getApplicationContext(),Main3_1Activity.class);
        startActivity(intent);

    }

    public void aula3_2Click(View view) {
        Intent intent = new Intent(getApplicationContext(),Main3_2Activity.class);
        startActivity(intent);

    }

    public void aula3_3Click(View view) {
        Intent intent = new Intent(getApplicationContext(),Main3_3Activity.class);
        startActivity(intent);

    }

    public void aula3_4Click(View view) {
        Intent intent = new Intent(getApplicationContext(),Main3_4Activity.class);
        startActivity(intent);

    }

}
