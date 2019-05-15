package com.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pi4.farol.R;

public class Adpt extends ArrayAdapter<String> {
    private Context context;
    private String[] dados;

    public Adpt(Context context
            , String[] dados) {
        super(context, android.R.layout.simple_list_item_1, dados);
        this.dados = dados;
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.adpt_model_1, null, true);

        TextView texto = v.findViewById(R.id.txtMsg);
        String nome = dados[position];
        texto.setText(nome);

        return v; //super.getView(position, convertView, parent);
    }
}
