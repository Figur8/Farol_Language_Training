package com.slumdev.farol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class FeedBackActivity extends AppCompatActivity {

    private Spinner pronuncia, gramatica, expressao, cores, numeros, horas ;
    private String language;

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

        ArrayAdapter adapter = ArrayAdapter.createFromResource(FeedBackActivity.this,
                R.array.Notas, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_drop_down);
        pronuncia.setAdapter(adapter);
        gramatica.setAdapter(adapter);
        expressao.setAdapter(adapter);
        cores.setAdapter(adapter);
        numeros.setAdapter(adapter);
        horas.setAdapter(adapter);
    }

    // Criando o menu da ActionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.voltar:
                Intent i = new Intent(FeedBackActivity.this, MainActivity.class);
                startActivity(i);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
    }
}
