package com.slumdev.farol;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.slumdev.farol.classes.Contacts;
import com.slumdev.farol.classes.User;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Spinner spinnerLanguage;
    private ImageView talk;
    private ImageView imagemUsuarioLogado;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private TextView usuarioLogado;

    //Firebase
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private User user = new User();

    View.OnClickListener toChat = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(spinnerLanguage.getSelectedItem().equals("Selecione")){
                Toast.makeText(MainActivity.this,"Você precisa informar um idioma!",Toast.LENGTH_SHORT).show();
            }else{
                Intent i = new Intent(MainActivity.this, ContactsActivity.class);
                i.putExtra("language", spinnerLanguage.getSelectedItem().toString());
                i.putExtra("user", user);
                startActivity(i);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        authVerify();
        searchUsers();

        // Este item é como se fosse um selectItem do html, precisamos instancialo como um listView
        spinnerLanguage = findViewById(R.id.option_language);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(MainActivity.this,
                R.array.Langauges, android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguage.setAdapter(adapter);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        talk = findViewById(R.id.img_talk);
        talk.setOnClickListener(toChat);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        /* O ActionBarDrawerToggle configura o ícone do aplicativo localizado à esquerda da barra
        de ação ou da barra de ferramentas para abrir e fechar o Navigation Drawer.
        Para poder criar uma instância de ActionBarDrawerToggle, precisamos fornecer os
        seguintes parâmetros:
            1 - Um contexto pai – por exemplo, pode ser a própria Activity, enquanto em um Fragment
                você chama getActivity();
            2 - Uma instância do DrawerLayout para vincular à ActionBar da Activity;
            3 - Uma instância da ToolBar para vincular o DrawerLayout a mesmo;
            4 - As Strings para as operações de abertura e fechamento respectivamente
                (para acessibilidade)
        Obs. Não esquecer de adicionar as strings dentro do strings.xml */

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        usuarioLogado = headerView.findViewById(R.id.nomeUsuario);
        imagemUsuarioLogado = headerView.findViewById(R.id.logo_imageView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            auth.signOut();
            usuarioLogado.setText("usuário");
            finish();
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Verifico se já possuo um usuário autenticado
    public void authVerify(){
        // Se for nulo o usuário não está logado, retorna para a activity login
        if(auth.getUid() == null){
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }else{
            user.setUuid(auth.getUid());
            Log.d("Usuario Logado: " , user.getUuid());
        }
    }

    /*
   Aqui eu procuro os usuários na coleção do firebase, pra resgatar o nome de usuário e a imagem do perfil.
   Usei uma classe que peguei no github chama Picasso, é pra setar a foto do banco no ImageView.
    */
    public void searchUsers(){
        FirebaseFirestore collectionsUsers = FirebaseFirestore.getInstance();
        collectionsUsers.collection("/users")
            .addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                    if (e != null) {
                        Log.e("Busca: ", e.getMessage());
                        return;
                    }
                    List<DocumentSnapshot> docs = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot doc : docs) {
                        user = doc.toObject(User.class);
                        if((user.getUuid()).equals(auth.getUid())){
                            usuarioLogado.setText(user.getUsername());
                            Picasso.get().load(user.getProfileUrl()).into(imagemUsuarioLogado);
                            Log.d("Usuário: ",user.getUuid());
                        }
                    }

                }
            });
    }

    private void fetchLastMessage() {
        String uid = user.getUuid();
        FirebaseFirestore colectionMessages = FirebaseFirestore.getInstance();
        colectionMessages.collection("/last-messages")
            .document(uid)
            .collection("contatos")
            .addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                    List<DocumentChange> documentChanges = queryDocumentSnapshots.getDocumentChanges();

                    if( documentChanges == null){
//                            Toast.makeText(MainActivity.this, "Não há menssagens!", Toast.LENGTH_SHORT).show();
                    }else {
                        for (DocumentChange doc : documentChanges) {
                            if (doc.getType() == DocumentChange.Type.ADDED) {
                                Contacts contact = doc.getDocument().toObject(Contacts.class);
                                mostrarNotificacao(contact.getUsername(), contact.getLastMessage());
                            }
                        }
                    }
                }
            });
    }

    // Aqui é o método pra mostrar a notificação. Eu chamo ele ai em cima logo após listar as ultimas mensagens...
    public void mostrarNotificacao(String title, String body){
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Isso aqui é uma intenção pendente, ou seja, só vou chamar ela quando for necessário... Passo via putExtra o usuário, mas nem sei se é necessário!
        PendingIntent p = PendingIntent.getActivity(MainActivity.this, 0,
                new Intent(MainActivity.this, ChatActivity.class)
                        .putExtra("userNotify",user),0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this)
                .setSmallIcon(R.mipmap.ic_launcher) // Icone da notificação
                .setContentTitle(title) // Titulo
                .setContentText(body) // A mensagem mesmo
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(p) // Aqui a intent
                .setAutoCancel(true); // Aqui é pra ela fechar automatico assim que clicar
        Notification n = builder.build(); // Aqui eu mostro!
        manager.notify(R.mipmap.ic_launcher, n);
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dash) {
            Toast.makeText(MainActivity.this, "Dashboard", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.nav_config){
            Toast.makeText(MainActivity.this, "Fazer alguma coisa aqui...", Toast.LENGTH_SHORT).show();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }
    }









}
