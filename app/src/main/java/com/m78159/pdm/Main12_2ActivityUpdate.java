package com.m78159.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main12_2ActivityUpdate extends AppCompatActivity {

    private String id;
    private EditText aula12UpdateTitle, aula12UpdateTime, aula12UpdateLat,
            aula12UpdateLon;
    private Main12_1ActivityDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12_2_update);

        helper = new Main12_1ActivityDatabaseHelper(getApplicationContext());

        TextView textViewId = findViewById(R.id.aula12UpdateId);
        aula12UpdateTitle = findViewById(R.id.aula12UpdateTitle);
        aula12UpdateTime = findViewById(R.id.aula12UpdateTime);
        aula12UpdateLat = findViewById(R.id.aula12UpdateLat);
        aula12UpdateLon = findViewById(R.id.aula12UpdateLon);

        Bundle extras = getIntent().getExtras();
        id = extras.getString("id");
        textViewId.setText(id);
        aula12UpdateTitle.setText(extras.getString("title"));
        aula12UpdateTime.setText(extras.getString("timeOfRecording"));
        aula12UpdateLat.setText(extras.getString("latitude"));
        aula12UpdateLon.setText(extras.getString("longitude"));
    }

    public void update(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", aula12UpdateTitle.getText().toString());
        values.put("timeOfRecording",aula12UpdateTime.getText().toString());
        values.put("latitude", aula12UpdateLat.getText().toString());
        values.put("longitude", aula12UpdateLon.getText().toString());

        long resultado = db.update("trabalho_audio", values, "id = ?", new String[]{id});
        if (resultado != -1){
            Toast.makeText(this,"Registro Alterato com Sucesso!", Toast.LENGTH_SHORT).show();
            super.finish();
        }else {
            Toast.makeText(this,"Erro ao Modificar!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy(){
        helper.close();
        super.onDestroy();
    }
}
