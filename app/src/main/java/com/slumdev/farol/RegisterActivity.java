package com.slumdev.farol;

import android.content.Intent;
//Imagem perfil
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
//Firebase
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
//Classes
import com.slumdev.farol.classes.User;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseFirestore collectUsers = FirebaseFirestore.getInstance();
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private EditText editUsername, editEmail, editPassword;
    private Button register;

    //Variáveis para inserção de imagem do perfil
    private StorageReference imageUri;
    private Uri uriImage;
    private Bitmap image;
    private ImageView imagePerfil;
    private Button selectImage;


    //Evento para selecionar imagem do dispositivo
    View.OnClickListener selecionarImagem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            selectImage();
        }
    };


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


        //Imagem perfil
        imagePerfil = findViewById(R.id.image_user_register);
        //Botão Selecionar Imagem, o próprio imageView
        selectImage = findViewById(R.id.button_select_image);
        selectImage.setOnClickListener(selecionarImagem);

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
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.w("Result: ", "CreateUserSucess!");
                            // Exibe uma mensagem caso sucesso!
                            Toast.makeText(RegisterActivity.this,"Sucesso ao criar Usuário! ", Toast.LENGTH_SHORT).show();
                            saveUser();
                        }else{
                            Log.w("Result: ", task.getException().toString());
                            // Exibe uma mensagem caso haja erro!
                            Toast.makeText(RegisterActivity.this,"Sucesso ao criar Usuário! " + task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

public void saveUser() {
    final String fileName = UUID.randomUUID().toString();
    imageUri = storage.getReference("/images/" + fileName);
    imageUri.putFile(uriImage)
        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
        @Override
        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            imageUri.getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Log.i("ImageUri: ", uri.toString());
                    Log.i("UpImage: ", "ImageUploadSucess");
                    String uid = auth.getUid();
                    String profileUrl = uri.toString();
                    String username = editUsername.getText().toString();
                    User user = new User();
                    user.setUuid(uid);
                    user.setUsername(username);
                    user.setProfileUrl(profileUrl);
                    collectUsers.collection("users")
                        .document(uid)
                        .set(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("SaveUser: ", "Document successfully written");
                                auth.signOut();
                                // Exibe uma mensagem caso sucesso!
                                Toast.makeText(RegisterActivity.this,"Sucesso ao criar Usuário! ", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("SaveUser", "Error writing document");
                                // Exibe uma mensagem caso haja erro!
                                Toast.makeText(RegisterActivity.this,"Erro ao criar Usuário! " + e, Toast.LENGTH_SHORT).show();
                            }
                        });
                }
            });
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
        ImageView image = imagePerfil;

        if( image.getDrawable() == null) {
            Toast.makeText(RegisterActivity.this, "Selecione uma Imagem!", Toast.LENGTH_LONG).show();
            Log.i("Iamge: ", "ImagemVazia");
        }
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
            imagePerfil.setImageDrawable(image.getDrawable());
            creatUser();
        }
        return validate;
    }

    /* Selecionando imagem a partir da galeria do dispositivo
      Se uma imagem não for selecionada no dispositivo uma imagem default será atribuida */
    public void selectImage(){
        Intent selectImage = new Intent(Intent.ACTION_PICK);
        selectImage.setType("image/jpeg");
        /* Recupero o resultado da inicialização da activity
           Através do requestCode eu percebo qual ação está agindo
           E através dele irei recuperar a imagem da galeria como método onActivityResult*/
        startActivityForResult(selectImage, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0 && resultCode == RESULT_OK && data.getData() != null){
            uriImage = data.getData();
        }else{
            validateRegister();
            return;
        }

        /* Em seguida defino uma variável tipo Bitmap vazia para armazenar a imagem
           Através da classe MediaStore eu pego as imagens do dispositivo */
        try {
            image = MediaStore.Images.Media.getBitmap(getContentResolver(), uriImage);
            imagePerfil.setImageDrawable(new BitmapDrawable(image));
            /* Deixando o botão transparente */
            selectImage.setAlpha(0);
        } catch (IOException e) {
            Toast.makeText(RegisterActivity.this,"Erro! " + e, Toast.LENGTH_SHORT).show();
            Log.w("Erro: ", e);

        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
        super.onBackPressed();
    }
}


//TODO - Salavar usuário dentro de uma coleção no firebaseFirestore.
// Colocar ou não uma imagem para registro do usuário?
// Verificar porque está dando erro no uriImage.