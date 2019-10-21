package com.m78159.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main9Activity extends AppCompatActivity {

    private EditText valor1;
    private EditText valor2;
    private EditText resultado;
    private Spinner operacao;
    private Main9_1ActivityAdapter adapter;
    private final String[] operacoes = {"+", "-", "*", "/"};
    private ExpandableListView list;
    private Map<String,String> lista_operacoes;
    // lista de headers com as datas
    private List<String> headers;
    // lista de dados
    private List<Main9_2ActivityDados> listData;
    /*
     * Map com:
     * TimeStamp do header como key
     * Classe de dados como value
     */
    private Map<String,Main9_2ActivityDados> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);

        valor1 = findViewById(R.id.aula9_valor1);
        valor2 = findViewById(R.id.aula9_valor2);
        resultado = findViewById(R.id.aula9_resultado);
        operacao = findViewById(R.id.spinner_operacao);
        list = findViewById(R.id.expandable_list);

        headers = new ArrayList<>();
        listData = new ArrayList<>();
        data = new HashMap<>();

        lista_operacoes = new HashMap<>();
        lista_operacoes.put(operacoes[0],"Adição");
        lista_operacoes.put(operacoes[1],"Subtração");
        lista_operacoes.put(operacoes[2],"Multiplicação");
        lista_operacoes.put(operacoes[3],"Divisão");

        adapter = new Main9_1ActivityAdapter(this.getApplicationContext(),headers,data);
        list.setAdapter(adapter);
    }


    public final void aula9Calcular(View view){
        @SuppressLint("SimpleDateFormat") String date = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime());
        double result = 0.0;

        switch(operacao.getSelectedItem().toString()){
            case "+":
                result = Double.parseDouble(valor1.getText().toString()) + Double.parseDouble(valor2.getText().toString());
                break;
            case "-":
                result = Double.parseDouble(valor1.getText().toString()) - Double.parseDouble(valor2.getText().toString());
                break;
            case "*":
                result = Double.parseDouble(valor1.getText().toString()) * Double.parseDouble(valor2.getText().toString());
                break;
            case "/":
                result = Double.parseDouble(valor1.getText().toString()) / Double.parseDouble(valor2.getText().toString());
                break;
        }
        resultado.setText(String.valueOf(result));

        if(!headers.contains(date))
            headers.add(date);

        data.put(date,new Main9_2ActivityDados(
                date,
                Double.parseDouble(valor1.getText().toString()),
                Double.parseDouble(valor2.getText().toString()),
                result,
                lista_operacoes.get(operacao.getSelectedItem().toString())));

        adapter.notifyDataSetChanged();
    }

}
