package com.m78159.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


public class Main6_2Activity extends AppCompatActivity {

    Spinner spinner;
    ListView listView;
    final String[] estates = {"Rio Grande do Sul", "Santa Catarina", "Paraná", "São Paulo", "Rio de Janeiro"};
    final String[][] cities = {
            {"Porto Alegre", "Rio Pardo", "Santa Cruz do Sul", "Pantano Grande", "Dom Feliciano"},
            {"Florianópolis", "Passo de Torres", "Vacaria", "Tubarão", "Criciúma"},
            {"Curitiba", "Ponta Grossa", "Cascavél", "Joinville", "Paranaguá"},
            {"São Paulo", "Guarujá", "Campinas", "Bauru", "Araraquara"},
            {"Rio de Janeiro", "Teresópolis", "Saquarema", "Cabo Frio", "Cordeiro"}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6_2);

        String nome = getIntent().getStringExtra("nome");
        final TextView t = findViewById(R.id.nome);
        t.setText(nome);

        spinner = findViewById(R.id.spinnerEstado);
        listView = findViewById(R.id.listviewCidade);

        ArrayAdapter<String> adapter_sp = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, estates);
        spinner.setAdapter(adapter_sp);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ArrayAdapter<String> adapter_lst =
                        new ArrayAdapter<>(getApplicationContext(), R.layout.list_layout, R.id.textViewList, cities[position]);
                listView.setAdapter(adapter_lst);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                listView.setAdapter(null);
            }

        });
        listView.setEmptyView(findViewById(R.id.listviewCidadeEmpty));
        ArrayAdapter<String> adapter_lst =
                new ArrayAdapter<>(getApplicationContext(), R.layout.list_layout, R.id.textViewList, cities[0]);
        listView.setAdapter(adapter_lst);
    }
}
