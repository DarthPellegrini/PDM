package com.m78159.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class Main6_1Activity extends ListActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6_1);
        setListAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, getNames()
        ));
        ListView listView = getListView();
        listView.setOnItemClickListener(this);
    }

    private List<String> getNames(){
        return Arrays.asList(
                "Fulano da Silva","Ciclano da Costa","Beltrano Azevedo","Hermanoteu da Pentescopéia","Micalatéia",
                "James Hetfield", "Kirk Hammett", "Cliff Burton", "Lars Ulrich", "Jason Newsted", "Roberto Agustín Santiago De la Cruz Trujillo");
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        //do nothing
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        TextView textView = (TextView) view;
        Intent intent = new Intent(this, Main6_2Activity.class);
        intent.putExtra("nome", textView.getText());
        startActivity(intent);
    }

}
