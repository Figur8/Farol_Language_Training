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

import java.io.IOException;
import java.util.UUID;


public class RegisterActivity extends AppCompatActivity {

    /* Autenticação no Firebase */
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private FirebaseFirestore collectionUsers = FirebaseFirestore.getInstance();
    private StorageReference imageUri;
    private EditText editEmail;
    private EditText editPassword;
    private EditText editUsername;
    private Button saveUser;

    //Variáveis para inserção de imagem do perfil
    private Button selectPhoto;
    private ImageView imageProfile;
    private Uri uriImage;
    private Bitmap bitmap;


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


        // Passamos os dados do usuário para persisntência em banco
        editUsername = findViewById(R.id.userName);
        editEmail = findViewById(R.id.userEmail);
        editPassword = findViewById(R.id.userPassword);

        //Imagem perfil
        imageProfile = findViewById(R.id.image_user_register);

        //Botões para efetuar o cadastro e selecionar a imagem
        saveUser = findViewById(R.id.buttonRegister);
        saveUser.setOnClickListener(registrar);

        selectPhoto = findViewById(R.id.button_select_image);
        selectPhoto.setOnClickListener(selecionarImagem);
    }

    // Cria um usuário com email e senha.
    //TODO - criar autenticação com o google
    private void createUser() {
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("Result: ", "Usuário criado com sucesso!");
                            saveUser();
                        } else {
                            Log.d("Result: ", task.getException().toString());
                            // Exibe uma mensagem caso haja erro!
                            Toast.makeText(RegisterActivity.this, "Erro ao criar usuário! " + task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Result: ", e.getMessage(), e);
                // Exibe uma mensagem caso haja erro!
                Toast.makeText(RegisterActivity.this, "Erro ao autenticar usuário! " +e.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    /* Adicionada a dependencia do storage do firebase para armazenamento da imagem do perfil.
     Envio dos dados do usuário juntamente com a foto do perfil
     crio uma hash aleatória "fileName" para ter um nome de arquivo unico
     crio uma referência ao storage por meio da classe StorageReference e passo o nome do
     arquivo, logos após crio uma URI para buscar meus arquivos dos arquivos locais dispositivo,
     através de um método que chamarei de selectPhoto*/

    public void saveUser() {
        final String fileName = UUID.randomUUID().toString(); // geração de id randomico, referencia um arquivo único
        imageUri = storage.getReference("/images/" + fileName);
        imageUri.putFile(uriImage)
            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    imageUri.getDownloadUrl()
                        .addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Log.d("ImageUri: ", uri.toString());
                                Log.d("UpImage: ", "Image upada com sucesso!");
                                String uid = auth.getUid();
                                String username = editUsername.getText().toString();
                                String profileUrl = uri.toString();
                                final User user = new User();
                                user.setUuid(uid);
                                user.setUsername(username);
                                user.setProfileUrl(profileUrl);
                                collectionUsers.collection("users")
                                    /* para gerar um identificador fixo... passamos um document com o uid e depois set no usuário  */
                                    .document(uid)
                                    .set(user)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d("Usuário: ", user.getUuid());
                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        // CLEAR_TASK, NEW_TASK, faz com que essa activity seja a principal.
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.i("FalhaUser: ", e.getMessage());
                                        }
                                    });
                                    }
                            });
                }
            }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {

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
        ImageView image = imageProfile;

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
            createUser();
        }
        return validate;
    }

    /* Abrindo a galeria através de uma intenção, usando uma ação do formato pick, obter... */
    public void selectImage(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*"); //qualquer tipo de imagem por isso o *
        intent.setAction(Intent.ACTION_GET_CONTENT);
        /* passo a intent e o código de requisição. Na volta   */
        startActivityForResult(intent, 0);
    }

    // este método me passa um código e o dado que eu peguei de uma outra intenção, serviço, aplicativo...
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Uri é o caminho onde a imagem está no smartphone
        if(requestCode == 0 && resultCode == RESULT_OK && data.getData() != null){
            uriImage = data.getData();
            imageProfile.setImageURI(uriImage);
        }else{
            validateRegister();
            return;
        }
        /* Em seguida eu instancio um bitmap e pego através da classe mMediaStore */
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriImage);
            imageProfile.setImageDrawable(new BitmapDrawable(bitmap));
            /* Deixando o botão transparente */
            selectPhoto.setAlpha(0); // esconde o botão na frente da foto.
        } catch (IOException e) {
           e.printStackTrace();
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
