package com.m78159.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Main2_1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_1);

        Intent intent = getIntent();
        String valor = intent.getStringExtra("resultado");
        TextView t = findViewById(R.id.resultTempC);
        t.setText(t.getText() + valor + " Fº");
    }

}