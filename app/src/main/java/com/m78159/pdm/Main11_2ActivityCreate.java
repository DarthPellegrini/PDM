package com.m78159.pdm;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Main11_2ActivityCreate extends AppCompatActivity {

    private Main11_1ActivityDataBaseHelper helper;
    private EditText modelo, valor, ano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11_2_create);


        modelo = findViewById(R.id.aula11_modeloAdd);
        valor = findViewById(R.id.aula11_valorAdd);
        ano = findViewById(R.id.aula11_anoAdd);

        helper = new Main11_1ActivityDataBaseHelper(this);

    }

    public void aula11CreateBtn(View view){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("modelo", modelo.getText().toString());
        values.put("ano", Integer.parseInt(ano.getText().toString()));
        values.put("valor", Double.parseDouble(valor.getText().toString()));

        long resultado = db.insert("carro", null, values);
        if (resultado != -1){
            Toast.makeText(this, "Registro salvo com sucesso!", Toast.LENGTH_SHORT).show();
            super.finish();
        }else{
            Toast.makeText(this, "Erro ao salvar o registro!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy(){
        helper.close();
        super.onDestroy();
    }
}
