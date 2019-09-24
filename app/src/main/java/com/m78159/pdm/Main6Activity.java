package com.m78159.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;

public class Main6Activity extends AppCompatActivity {

    Spinner spinner;
    ListView listView;
    String [] categoria_despesa = {"Passagem", "Alimentação", "Estadia", "Outros"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        spinner = (Spinner) findViewById(R.id.spinner);
        listView =  (ListView) findViewById(R.id.listview);

        ArrayAdapter<CharSequence> adapter_sp = ArrayAdapter.createFromResource(this, R.array.categoria_gasto,android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter_sp);

        final ArrayAdapter<String> adapter_lst = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Arrays.asList(categoria_despesa));
        listView.setAdapter(adapter_lst);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Mensagem(categoria_despesa[position]);
            }
        });
    }

    private void Mensagem(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {

        super.onCreateOptionsMenu(menu);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.meumenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemNew:
                Toast.makeText(this, "Novo!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.itemSave:
                Toast.makeText(this, "Salvar!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.itemAdd:
                Toast.makeText(this, "Adicionar!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.itemListas:
                Intent intent = new Intent(this, Main6_1Activity.class);
                startActivity(intent);

        }
        return false;
    }
}
