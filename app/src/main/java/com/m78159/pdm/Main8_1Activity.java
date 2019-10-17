package com.m78159.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Main8_1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8_1);

        Bundle extras = getIntent().getExtras();
        String matricula = (String) extras.get("matricula");
        String nome = (String) extras.get("nome");
        String email = (String) extras.get("email");
        String estado = (String) extras.get("estado");
        String cidade = (String) extras.get("cidade");
        TextView revisao_show_matricula = (TextView) findViewById(R.id.revisao_show_matricula);
        TextView revisao_show_nome = (TextView) findViewById(R.id.revisao_show_nome);
        TextView revisao_show_email = (TextView) findViewById(R.id.revisao_show_email);
        TextView revisao_show_estado = (TextView) findViewById(R.id.revisao_show_estado);
        TextView revisao_show_cidade = (TextView) findViewById(R.id.revisao_show_cidade);
        revisao_show_matricula.setText("Matrícula: " + matricula);
        revisao_show_nome.setText("Nome: " + nome);
        revisao_show_email.setText("Email: " + email);
        revisao_show_estado.setText("Estado: " + estado);
        revisao_show_cidade.setText("Cidade: " + cidade);
    }
}
