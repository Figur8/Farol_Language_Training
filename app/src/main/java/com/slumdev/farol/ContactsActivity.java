package com.slumdev.farol;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.slumdev.farol.classes.User;
import com.squareup.picasso.Picasso;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.OnItemClickListener;
import com.xwray.groupie.ViewHolder;

import java.util.List;

import javax.annotation.Nullable;

public class ContactsActivity extends AppCompatActivity {


    private FirebaseFirestore fetchContacts = FirebaseFirestore.getInstance();
    private GroupAdapter adapter = new GroupAdapter(); // Essa classe é como uma framework que gera um outro tipo de adapter pro recyvle view
    private RecyclerView rvContacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        // Instancio o recycleView seto um adapter e um layout.
        rvContacts = findViewById(R.id.rv_contacts);
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));

        // Aqui é o evento que leva a imagem da lista para a sala do chat...
        // Preferi criar os contatos porque não consegui fazer aquela parada de ontem
        // Mas tá suave...
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull Item item, @NonNull View view) {
                Intent i = new Intent(ContactsActivity.this, ChatActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        fetchUsers();
    }


    // Travei aqui o botão de voltar... pra não gerar conflitos nas activitys
    // Coloquei um botão de retorno na actiobar.
    @Override
    public void onBackPressed() {
    }

    //Método para procurar contatos numa coleção no firestore
    private void fetchUsers() {
        fetchContacts.collection("/users")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.e("Busca: ", e.getMessage());
                            return;
                        }
                        // Crio aqui uma lista que irá armazenar os documentos da coleção de usuários
                        List<DocumentSnapshot> docs = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot doc : docs) {
                            User newUser = doc.toObject(User.class);
                            Log.d("Usuário: ", newUser.getUsername());
                            adapter.add(new ContactItem(newUser));
                        }
                    }
                });
    }

    //Aqui está a classe interna que irá gerenciar cada item da lista
    // extende o Item que precisa de um ViewHolder(lembrar de pesquisar)
    private class ContactItem extends Item<ViewHolder> {

        private final User user;

        private ContactItem(User user){
            this.user = user;
        }

        @Override
        public void bind(@NonNull ViewHolder viewHolder, int position) {
            TextView nomecontato = viewHolder.itemView.findViewById(R.id.nameContact);
            ImageView imagemcontato = viewHolder.itemView.findViewById(R.id.imageContact);

            nomecontato.setText(user.getUsername());
            Picasso.get().load(user.getProfileUrl()).into(imagemcontato);
        }


        // serve para modificar os itens da lista
        @Override
        public int getLayout() {
            return R.layout.item_user;
        }
    }


    // Criando o menu da ActionBar que criei acima
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contacts, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.voltar_main:
                Intent i = new Intent(ContactsActivity.this, MainActivity.class);
                startActivity(i);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
