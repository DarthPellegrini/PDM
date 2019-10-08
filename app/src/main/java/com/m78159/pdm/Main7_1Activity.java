package com.m78159.pdm;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main7_1Activity extends ListActivity implements AdapterView.OnItemClickListener{

    //Labels e views
    private String[] labels = {"logotipo", "time", "pontuacao"};
    private int[] views = {R.id.logoTime, R.id.nomeTime, R.id.pontosTime};

    //Dados da lista
    private List<HashMap<String,Object>> list;
    private String[] clubes = {"Grêmio","Internacional","São Paulo","Palmeiras","Flamengo","Atlético-MG","Cruzeiro","Santos","Fluminense","Corinthians","América-MG","Vitória","Bahia","Atlético-PR","Botafogo","Vasco","Sport","Ceará","Chapecoense","Paraná Clube"};
    private int[] pontos = {49,49,46,44,41,38,33,31,31,30,30,29,28,27,26,24,24,24,22,16};
    private int[] logos = {R.drawable.gre, R.drawable.inter, R.drawable.sao, R.drawable.pal, R.drawable.fla, R.drawable.cam, R.drawable.cru, R.drawable.san, R.drawable.flu, R.drawable.cor, R.drawable.ame, R.drawable.vit, R.drawable.bah, R.drawable.cap, R.drawable.bot, R.drawable.vas, R.drawable.spt, R.drawable.cea, R.drawable.cha, R.drawable.par};
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Main7_1ActivityAdapter adapter
                = new Main7_1ActivityAdapter(this, dadosTabela(), R.layout.activity_main7_1, labels, views);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    private List<HashMap<String,Object>> dadosTabela() {
        list = new ArrayList<>();
        HashMap<String, Object> items;
        for (int i = 0; i < clubes.length; i++){
            items = new HashMap<>();
            items.put(labels[0],logos[i]);
            items.put(labels[1],clubes[i]);
            items.put(labels[2],pontos[i]);
            list.add(items);
        }
        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        HashMap<String, Object> item = list.get(position);
        Toast.makeText(getApplicationContext(),
                        "Time: " + item.get(labels[1]) +
                        "\nPontuação: " + item.get(labels[2]) ,
                        Toast.LENGTH_SHORT).show();
    }
}
