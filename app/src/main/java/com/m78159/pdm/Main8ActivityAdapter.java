package com.m78159.pdm;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main8ActivityAdapter extends SimpleAdapter {

    private final Context context;
    private final int textViewResourceId;
    List<Map<String,Object>> memory;

    public Main8ActivityAdapter(Context context, List<Map<String, Object>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.memory = data;
        this.context = context;
        this.textViewResourceId = resource;
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.Q)
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View row = layoutInflater.inflate (this.textViewResourceId, parent, false);

        if(position != -1 && position < memory.size()) {

            TextView tmat = (TextView) row.findViewById(R.id.revisaoMatriculaLista);
            TextView tnome = (TextView) row.findViewById(R.id.revisaoNomeLista);
            ImageView iFoto = (ImageView) row.findViewById(R.id.revisaoFotoLista);
            tmat.setText((memory.get(position).get("matricula").toString()));
            tnome.setText((memory.get(position).get("nome").toString()));
            iFoto.setImageBitmap(((Bitmap) memory.get(position).get("foto")));
        }
        return row;
    }

}
