package com.m78159.pdm;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main12_4ActivityList extends ListActivity implements AdapterView.OnItemClickListener {

    private String[] de = {"id", "title"};
    private int[] para = {R.id.trabalho_list_item_id, R.id.trabalho_list_item_title};
    private List<HashMap<String, Object>> registros = new ArrayList<>();
    private Main12_1ActivityDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12_4_list);

        helper = new Main12_1ActivityDatabaseHelper(getApplicationContext());

        buildList();

        if(registros.size() > 0) {
            SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), registros, R.layout.audio_list_layout, de, para);

            setListAdapter(adapter);
            getListView().setOnItemClickListener(this);
        } else {
            Toast.makeText(getApplicationContext(), "Não há nenhuma gravação salva!",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void buildList() {
        String query = "SELECT * FROM trabalho_audio";

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();
        
        if(cursor.isFirst()) {
            do {
                HashMap<String, Object> item = new HashMap<>();

                item.put("id", cursor.getInt(cursor.getColumnIndex("id")));
                item.put("title", cursor.getString(cursor.getColumnIndex("title")));
                item.put("timeOfRecording", cursor.getString(cursor.getColumnIndex("timeOfRecording")));

                registros.add(item);
            } while (cursor.moveToNext());
            cursor.close();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HashMap<String, Object> item = registros.get(position);
        Intent intent = new Intent(getApplicationContext(), Main12_3ActivityAudioPlayer.class);
        intent.putExtra("id", (int)item.get("id"));
        startActivity(intent);
    }

    @Override
    protected void onDestroy(){
        helper.close();
        super.onDestroy();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        this.recreate();
    }
}
