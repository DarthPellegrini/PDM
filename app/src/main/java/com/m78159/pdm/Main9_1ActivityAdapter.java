package com.m78159.pdm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main9_1ActivityAdapter extends BaseExpandableListAdapter {

    Context context;
    List<String> headers;
    Map<String,Double> headerData;
    Map<String,Map<String,Object>> childData;

    public Main9_1ActivityAdapter(Context context, List<String> headers, Map<String,Double> headerData, Map<String,Map<String,Object>> childData){
        this.context = context;
        this.headers = headers;
        this.headerData = headerData;
        this.childData = childData;
    }

    @Override
    public int getGroupCount() {
        return headerData.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return childData.get(headers.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return headers.get(i);
    }

    @Override
    public Map<String,Object> getChild(int i, int i1) {
        return childData.get(headers.get(i));
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = ((LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.activity_main9_expandable_list_header, null);
        }
        TextView date = view.findViewById(R.id.aula9_header_timestamp);
        TextView result = view.findViewById(R.id.aula9_header_resultado);

        date.setText("Data/hora = " + headers.get(i));
        result.setText("Resultado = " + headerData.get(headers.get(i)));

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = ((LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.activity_main9_expandable_list_child, null);
        }

        TextView valor1 = view.findViewById(R.id.aula9_child_valor1);
        TextView valor2 = view.findViewById(R.id.aula9_child_valor2);
        TextView operacao = view.findViewById(R.id.aula9_child_operacao);

        Map<String,Object> dados = childData.get(headers.get(i));

        valor1.setText("Valor 1 = " + dados.get("valor1"));
        valor2.setText("Valor 2 = " + dados.get("valor2"));
        operacao.setText("Operação = " + dados.get("operacao"));

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
