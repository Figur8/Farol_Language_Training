package com.slumdev.farol;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.UUID;


public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseFirestore collectUsers = FirebaseFirestore.getInstance();
    private FirebaseUser user;
    private EditText editUsername, editEmail, editPassword;
    private Button register;


    View.OnClickListener registrar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            validateRegister();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        editUsername = findViewById(R.id.register_userName);
        editEmail = findViewById(R.id.register_userLogin);
        editPassword = findViewById(R.id.register_userPassword);

        register = findViewById(R.id.btn_register);
        register.setOnClickListener(registrar);

    }

    public void creatUser(){
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d("Result: ", "CreateUserSucess!");
                            user = auth.getCurrentUser();
                            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(i);
                            finish();
                        }else{
                            Log.d("Result: ", task.getException().toString());
                        }
                    }
                });
    }

    public void saveUser(){
        final String fileName = UUID.randomUUID().toString();
        String username = editUsername.getText().toString();
        String uid = auth.getUid();
        User user = new User();
        user.setUuid(uid);
        user.setUsername(username);
        collectUsers.collection("users").document(uid)
                .set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                auth.signOut();
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            }
        });
    }

    // Método de validação dos dados do registro
    private boolean validateRegister(){
        boolean validate = true;

        /* verificando se os campos email e passowrod foram preenchidos */
        String name = editUsername.getText().toString();
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        if( name.isEmpty()) {
            editUsername.setError("Campo obrigatório!");
            Log.i("Nome: ", "NomeVazio");
        }else if(email.isEmpty()){
            editEmail.setError("Campo obrigatório!");
            Log.i("Email: ", "EmailVazio");
        }else if ( password.isEmpty()) {
            editPassword.setError("Campo obrigatório!");
            Log.i("Password: ", "PasswordVazio");
        }else{
            editUsername.setError(null);
            editEmail.setError(null);
            editPassword.setError(null);
            creatUser();
        }
        return validate;
    }

}


//TODO - fazer autenticação junto ao firebase