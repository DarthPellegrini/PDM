package com.m78159.pdm;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

@SuppressWarnings("RedundantCast")
public class Main7_2ActivityAdapter extends ArrayAdapter {

    private String[] colorNames;
    private int[] colors;

    protected Main7_2ActivityAdapter(Context context, int textViewResourceId, String[] objects, int[] colors) {
        super(context, textViewResourceId, objects);
        this.colorNames = objects;
        this.colors = colors;
    }

    private View getCustomView(int position, View convertView, ViewGroup parent){
        LayoutInflater layoutInflater = ((Activity)super.getContext()).getLayoutInflater();
        View row = layoutInflater.inflate (R.layout.activity_main7_2_adapter, parent, false);

        TextView tv1 = (TextView) row.findViewById (R.id.colorSpinnerTextView);
        tv1.setText(colorNames[position]);
        tv1.setBackgroundColor(colors[position]);

        return row;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
}
