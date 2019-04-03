package com.pi4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pi4.farol.R;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button login;
    private Button login_google;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        username = findViewById(R.id.edit_username);
        password = findViewById(R.id.edit_password);
        login = findViewById(R.id.btn_login);
        login_google = findViewById(R.id.btn_google);

        login.setOnClickListener(logon);
        login_google.setOnClickListener(logon);

    }

    View.OnClickListener logon = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if(id == R.id.btn_login) {
                Toast.makeText(LoginActivity.this, "Login com Email", Toast.LENGTH_SHORT).show();
                Intent email = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(email);
            }else if (id == R.id.btn_google){
                Toast.makeText(LoginActivity.this,"Login com Google", Toast.LENGTH_SHORT).show();
                Intent google = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(google);
            }
        }
    };

}
