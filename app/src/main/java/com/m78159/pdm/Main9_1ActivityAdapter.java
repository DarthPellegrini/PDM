package com.m78159.pdm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class Main9_1ActivityAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> headers;
    private Map<String,Main9_2ActivityDados> data;

    protected Main9_1ActivityAdapter(Context context, List<String> headers, Map<String,Main9_2ActivityDados> data){
        this.context = context;
        this.headers = headers;
        this.data = data;
    }

    @Override
    public int getGroupCount() {
        return headers.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return headers.get(i);
    }

    @Override
    public Main9_2ActivityDados getChild(int i, int i1) {
        return data.get(headers.get(i));
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

        date.setText(String.format("Data/hora = %s", this.getGroup(i)));
        result.setText(String.format("Resultado = %s", this.getChild(i,0).getResultado()));

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = ((LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.activity_main9_expandable_list_child, null);
        }

        TextView valores = view.findViewById(R.id.aula9_child_valores);
        TextView operacao = view.findViewById(R.id.aula9_child_operacao);

        valores.setText(String.format("Valor 1 = %s      Valor 2 = %s",
                this.getChild(i, i1).getValor1(), this.getChild(i, i1).getValor2()));
        operacao.setText(String.format("Operação = %s", this.getChild(i, i1).getOperacao()));

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
