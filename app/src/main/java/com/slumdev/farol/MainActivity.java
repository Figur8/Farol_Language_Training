package com.slumdev.farol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.slumdev.farol.classes.User;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Spinner spinner;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ImageView talk;
    private User user;

    //Firebase
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        authVerify();

        // Este item é como se fosse um selectItem do html, precisamos instancialo como um listView

        spinner = findViewById(R.id.option_language);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MainActivity.this,
                R.array.Langauges, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        talk = findViewById(R.id.img_talk);
        talk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(i);
                //Toast.makeText(MainActivity.this, "Abriu Chat", Toast.LENGTH_SHORT).show();
            }
        });
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
            finish();
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            return true;
        }else if(id == R.id.action_feedback){
            Intent i = new Intent(MainActivity.this, FeedBackActivity.class);
            startActivity(i);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dash) {
            Toast.makeText(MainActivity.this, "Dashboard", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_logout) {
            Toast.makeText(MainActivity.this, "Logout", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.nav_chat){
            getChat();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Abrir activity do Chat
    public void getChat(){
        // Resgatando valores da classe User e enviando para a Activity Chat
        //user = (User) getIntent().getExtras().get("currentUser");
        Intent i = new Intent(MainActivity.this, ChatActivity.class);
        //i.putExtra("id", user);
        startActivity(i);
    }

    // Verifico se já possuo um usuário autenticado
    public void authVerify(){
        if(auth.getCurrentUser() == null){
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }else{
            Toast.makeText(MainActivity.this,"Usuário logado: "+ auth.getCurrentUser() , Toast.LENGTH_SHORT).show();
        }
    }
}

//TODO - Verificar qual a falha no Firebase. Não consigo registrar as imagens no FirestoreStorage
// e nem consigo criar as coleções.