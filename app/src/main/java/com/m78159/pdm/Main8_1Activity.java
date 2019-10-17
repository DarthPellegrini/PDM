package com.m78159.pdm;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class Main8_1Activity extends AppCompatActivity {

    @Override
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8_1);

        Bundle extras = getIntent().getExtras();
        String matricula = (String) Objects.requireNonNull(extras).get("matricula");
        String nome = (String) Objects.requireNonNull(extras).get("nome");
        String email = (String) Objects.requireNonNull(extras).get("email");
        String estado = (String) Objects.requireNonNull(extras).get("estado");
        String cidade = (String) Objects.requireNonNull(extras).get("cidade");
        TextView revisao_show_matricula = findViewById(R.id.revisao_show_matricula);
        TextView revisao_show_nome = findViewById(R.id.revisao_show_nome);
        TextView revisao_show_email = findViewById(R.id.revisao_show_email);
        TextView revisao_show_estado = findViewById(R.id.revisao_show_estado);
        TextView revisao_show_cidade = findViewById(R.id.revisao_show_cidade);
        revisao_show_matricula.setText(String.format("Matrícula: %s", matricula));
        revisao_show_nome.setText(String.format("Nome: %s", nome));
        revisao_show_email.setText(String.format("Email: %s", email));
        revisao_show_estado.setText(String.format("Estado: %s", estado));
        revisao_show_cidade.setText(String.format("Cidade: %s", cidade));
    }
}
