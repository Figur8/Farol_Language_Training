package com.slumdev.farol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class FeedBackActivity extends AppCompatActivity {

    private Spinner pronuncia, gramatica, expressao, cores, numeros, horas ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        pronuncia = findViewById(R.id.spinner_pronuncy);
        gramatica = findViewById(R.id.spinner_grammar);
        expressao = findViewById(R.id.spinner_expressions);
        cores = findViewById(R.id.spinner_colors);
        numeros = findViewById(R.id.spinner_numbers);
        horas = findViewById(R.id.spinner_hours);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(FeedBackActivity.this,
                R.array.Notas, android.R.layout.simple_spinner_dropdown_item);
        pronuncia.setAdapter(adapter);
        gramatica.setAdapter(adapter);
        expressao.setAdapter(adapter);
        cores.setAdapter(adapter);
        numeros.setAdapter(adapter);
        horas.setAdapter(adapter);
    }
}
