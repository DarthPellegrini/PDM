package com.m78159.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main11Activity extends AppCompatActivity {

    private Main11_1ActivityDataBaseHelper helper;
    private EditText txtAno;
    private ListView lista;
    List<Map<String,Object>> carros;
    String [] de = {"id","modelo","ano","valor"};
    int[] para = {R.id.aula11_list_item_id,R.id.aula11_list_item_modelo,
                    R.id.aula11_list_item_ano, R.id.aula11_list_item_valor};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);

        txtAno = findViewById(R.id.aula11_ano);
        lista = findViewById(R.id.aula11_list);
        helper = new Main11_1ActivityDataBaseHelper(this);
    }

    public void aula11ReadBtn(View view){
        String busca = txtAno.getText().toString();
        String query = "SELECT * FROM carro" +
                (busca.isEmpty() ? "" : " WHERE ano = " + busca);
        carros = listarCarros(query);
        lista.setAdapter(new SimpleAdapter(this, carros, R.layout.activity_main11_list_layout, de, para));
        lista.setClickable(true);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String idDados = String.valueOf(carros.get(i).get("id"));
                Intent intent = new Intent(Main11Activity.this, Main11_3ActivityUpdateAndDelete.class);
                intent.putExtra("idDados", idDados);
                carros = new ArrayList<>();
                startActivity(intent);
            }
        });
    }

    @SuppressLint("DefaultLocale")
    private List<Map<String,Object>> listarCarros(String query){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        carros = new ArrayList<>();
        for (int i = 0; i < cursor.getCount(); i++){
            Map<String, Object> item = new HashMap<>();
            String id = cursor.getString(0);
            String modelo = cursor.getString(1);
            int ano = cursor.getInt(2);
            double valor = cursor.getDouble(3);
            item.put("id",id);
            item.put("modelo","Modelo: " + modelo);
            item.put("ano","Ano: " + ano);
            item.put("valor",String.format("R$ %.2f", valor));
            carros.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        return carros;
    }

    public void aula11OpenCreateActivity(View view){
        Intent intent = new Intent(getApplicationContext(),Main11_2ActivityCreate.class);
        carros = new ArrayList<>();
        startActivity(intent);
    }

    @Override
    protected void onDestroy(){
        helper.close();
        super.onDestroy();
    }
}
