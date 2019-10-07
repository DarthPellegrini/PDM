package com.m78159.pdm;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

public class Main7_1ActivityAdapter extends SimpleAdapter {

    protected Main7_1ActivityAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        if(position < 6)
            view.setBackgroundColor(0xFFFFFF00);
        else
            if(position > 15)
                view.setBackgroundColor(0xFFEE0000);
            else
                if(position % 2 != 0)
                    view.setBackgroundColor(0x00000000);
                else
                    view.setBackgroundColor(0xCCCCCCCC);

        return view;
    }
}
