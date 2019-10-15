package com.m78159.pdm;

import android.app.Activity;
import android.content.Context;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main8ActivityAdapter extends SimpleAdapter {


    private int textViewResourceId;
    private Object[] objects;
    private Context context;

    public Main8ActivityAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to, Object[] objects) {
        super(context, data, resource, from, to);
        this.objects = objects;
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private View getCustomView(int position, View convertView, ViewGroup parent){
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View row = layoutInflater.inflate (this.textViewResourceId, parent, false);

        TextView tmat = (TextView) row.findViewById (R.id.revisaoMatriculaLista);
        TextView tnome = (TextView) row.findViewById (R.id.revisaoNomeLista);
        ImageView iFoto = (ImageView) row.findViewById (R.id.revisaoFotoLista);
        tmat.setText(((TextView)objects[0]).getText());
        tnome.setText(((TextView)objects[1]).getText());
        iFoto.setImageResource(((ImageView)objects[2]).getSourceLayoutResId());

        return row;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        return view;
    }


}
