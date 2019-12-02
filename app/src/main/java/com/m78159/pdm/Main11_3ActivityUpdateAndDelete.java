package com.m78159.pdm;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class Main11_3ActivityUpdateAndDelete extends AppCompatActivity {

    String idDados = "";
    private Main11_1ActivityDataBaseHelper helper;
    private EditText modelo, valor, ano;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11_3_update_and_delete);

        Bundle extras = getIntent().getExtras();
        idDados = Objects.requireNonNull(extras).getString("idDados");

        modelo = findViewById(R.id.aula11_modeloUpDel);
        valor = findViewById(R.id.aula11_valorUpDel);
        ano = findViewById(R.id.aula11_anoUpDel);

        helper = new Main11_1ActivityDataBaseHelper(this);
        autoFillFields();
    }

    private void autoFillFields() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("select * FROM carro WHERE id = %s", idDados), null);
        cursor.moveToFirst();
        modelo.setText(cursor.getString(1));
        ano.setText(String.valueOf(cursor.getInt(2)));
        valor.setText(String.valueOf(cursor.getDouble(3)));
        cursor.close();
    }

    public void aula11UpdateBtn(View view){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("modelo", modelo.getText().toString());
        values.put("ano",Integer.parseInt(ano.getText().toString()));
        values.put("valor", Double.parseDouble(valor.getText().toString()));

        long resultado = db.update("carro", values, "id = ?", new String[]{idDados});
        if (resultado != -1){
            Toast.makeText(this,"Registro Alterado com Sucesso!", Toast.LENGTH_SHORT).show();
            super.finish();
        }else {
            Toast.makeText(this,"Erro ao salvar alterações!", Toast.LENGTH_SHORT).show();
        }
    }

    public void aula11DeleteBtn(View view){
        SQLiteDatabase db = helper.getWritableDatabase();

        long resultado = db.delete("carro", "id = ?", new String[]{idDados});
        if (resultado != -1){
            Toast.makeText(this, "Registro Excluído com Sucesso!", Toast.LENGTH_SHORT).show();
            super.finish();
        }else{
            Toast.makeText(this, "Erro ao excluir!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy(){
        helper.close();
        super.onDestroy();
    }
}
