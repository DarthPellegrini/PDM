package com.m78159.pdm;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main8Activity extends AppCompatActivity {

    private final int TIRAR_FOTO = 0;
    private Spinner spinnerEstado;
    private Spinner spinnerCidade;
    private ListView listAlunos;
    private Button capturar;
    private TextView matricula;
    private TextView nome;
    private TextView email;
    private ImageView captura;
    private Button adicionar;
    private ArrayList<ArrayList<Object>> memory = new ArrayList<>();

    //Labels e views
    private String[] labels = {"foto", "matricula", "nome"};
    private int[] views = {R.id.revisaoFotoLista, R.id.revisaoMatriculaLista, R.id.revisaoNomeLista};
    private List<HashMap<String,Object>> list;

    final String[] estates = {"Selecione o Estado","Rio Grande do Sul", "Santa Catarina", "Paraná", "São Paulo", "Rio de Janeiro"};
    final String[][] cities = {
            {"Selecione o Estado"},
            {"Porto Alegre", "Rio Pardo", "Santa Cruz do Sul", "Pantano Grande", "Dom Feliciano"},
            {"Florianópolis", "Passo de Torres", "Vacaria", "Tubarão", "Criciúma"},
            {"Curitiba", "Ponta Grossa", "Cascavél", "Joinville", "Paranaguá"},
            {"São Paulo", "Guarujá", "Campinas", "Bauru", "Araraquara"},
            {"Rio de Janeiro", "Teresópolis", "Saquarema", "Cabo Frio", "Cordeiro"}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        spinnerEstado = findViewById(R.id.revisaoEstado);
        spinnerCidade = findViewById(R.id.revisaoCidade);
        listAlunos = findViewById(R.id.listMatriculas);
        capturar = findViewById(R.id.revisaoCapturar);
        matricula = findViewById(R.id.revisaoMatricula);
        nome = findViewById(R.id.revisaoNome);
        email = findViewById(R.id.revisaoEmail);
        captura = findViewById(R.id.revisaoFotoCapturada);
        adicionar = findViewById(R.id.revisaoAdicionar);

        ArrayAdapter<String> adapter_sp = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, estates);
        spinnerEstado.setAdapter(adapter_sp);
        spinnerEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ArrayAdapter<String> adapter_lst =
                        new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, cities[position]);
                spinnerCidade.setAdapter(adapter_lst);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                spinnerCidade.setAdapter(null);
            }
        });

        Main8ActivityAdapter adapter =new Main8ActivityAdapter(this,
                dados(),
                R.layout.activity_main8_adapter,
                labels, views, memory.toArray());
        listAlunos.setAdapter(adapter);
        listAlunos.setOnItemClickListener( new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                HashMap<String, Object> item = list.get(position);
                //abre nova activity e passa os dados do item selecionado
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void takePictureClick(View view){
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CAMERA}, TIRAR_FOTO);
        } else {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, TIRAR_FOTO);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
       if (requestCode == TIRAR_FOTO)
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, TIRAR_FOTO);
                } else
                    Toast.makeText(this, "permissão de acesso à câmera negada", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TIRAR_FOTO && resultCode == RESULT_OK) {
            ImageView iv = findViewById(R.id.revisaoFotoCapturada);
            iv.setImageBitmap((Bitmap) data.getExtras().get("data"));
        }

    }

    public void addAluno(View view){
        ArrayList<Object> dados = new ArrayList<>();
        dados.add(matricula);
        dados.add(nome);
        dados.add(email);
        dados.add(spinnerEstado.getSelectedItem());
        dados.add(spinnerCidade.getSelectedItem());
        dados.add(captura);
        memory.add(dados);
    }

    private List<HashMap<String,Object>> dados() {
        list = new ArrayList<>();
        list.add(new HashMap<String, Object>());
        return list;
    }


}
