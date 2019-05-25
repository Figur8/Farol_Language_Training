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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.slumdev.farol.classes.Contacts;
import com.slumdev.farol.classes.Message;
import com.slumdev.farol.classes.User;
import com.squareup.picasso.Picasso;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class ChatActivity extends AppCompatActivity{

    private FirebaseFirestore colletctionConversas = FirebaseFirestore.getInstance();
    private FirebaseFirestore collectionUsers = FirebaseFirestore.getInstance();
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private EditText editdMsg;
    private Button btnSendMsg;
    private GroupAdapter adapter = new GroupAdapter();
    private RecyclerView rvMessage;
    private User userContact = new User();
    private User eumesmo = new User();


    View.OnClickListener send = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sendMessage();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        userContact = (User) getIntent().getExtras().get("userContact");
        getSupportActionBar().setTitle(userContact.getUsername());

        btnSendMsg = findViewById(R.id.btn_send_msg);
        btnSendMsg.setOnClickListener(send);
        editdMsg = findViewById(R.id.edit_send_msg);

        rvMessage = findViewById(R.id.rv_message_chat);
        rvMessage.setLayoutManager(new LinearLayoutManager(ChatActivity.this));
        rvMessage.setAdapter(adapter);

        /*
        Escutando os eventos dos usuário aqui mesmo... pegamos a coleção de usuários e buscamos nosso usuário logado.
        quando encontro o usuário eu chamo o método fetchMessage... para mostrar as msgs nos balões
         */
        collectionUsers.collection("/users")
        .document(auth.getUid())
        .get()
        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                eumesmo = documentSnapshot.toObject(User.class);
                fetchMessage();
            }

        });
    }

    public void fetchMessage() {
        if(eumesmo != null){
            String fromId = eumesmo.getUuid();
            String toId = userContact.getUuid();

            colletctionConversas.collection("/conversas")
            .document(fromId)
            .collection(toId)
            .orderBy("timestamp", Query.Direction.ASCENDING)
            .addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                    List<DocumentChange> documentChanges = queryDocumentSnapshots.getDocumentChanges();
                    if(documentChanges != null){
                        for (DocumentChange doc : documentChanges ) {
                            // getType me diz se o tipo do documento é ADDED, ou seja, objeto adicionado recente
                            if( doc.getType() == DocumentChange.Type.ADDED){
                                Message message = doc.getDocument().toObject(Message.class);
                                adapter.add(new MessageItem(message));
                            }

                        }
                    }
                }
            });
        }
    }

    private class MessageItem extends Item<ViewHolder>{

        private final Message message;

        private MessageItem(Message message) {
            this.message = message;
        }

        @Override
        public void bind(@NonNull ViewHolder viewHolder, int position) {
            TextView msg = viewHolder.itemView.findViewById(R.id.mensagem);
            ImageView img = viewHolder.itemView.findViewById(R.id.imagemMsg);

            msg.setText(message.getTexto());
            Picasso.get().load(userContact.getProfileUrl()).into(img);
        }

        @Override
        public int getLayout() {
            return  message.getFromId().equals(auth.getUid()) ? R.layout.item_from_msg : R.layout.item_to_msg;
        }
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
            case R.id.voltar_contacts:
                Intent i = new Intent(ChatActivity.this, ContactsActivity.class);
                startActivity(i);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Aqui a mágica acontece...
    private void sendMessage(){
        String texto = editdMsg.getText().toString();
        editdMsg.setText(null);

        final String fromId = auth.getUid();
        final String toId = userContact.getUuid();
        long timestamp = System.currentTimeMillis();

        //Corpo da mensagem
        final Message message = new Message();
        message.setFromId(fromId);
        message.setToId(toId);
        message.setTimestamp(timestamp);
        message.setTexto(texto);

        //Salvando as conversas na coleção do firestore enviando
        if(!message.getTexto().isEmpty()){
            colletctionConversas.collection("/conversas")
            .document(fromId)
            .collection(toId)
            .add(message)
            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Log.d("Teste: ", documentReference.getId());
                }
            }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            Log.e("Teste: ", e.getMessage(), e);
        }});
        //Salvando as conversas na coleção recebendo do outro lado
             colletctionConversas.collection("/conversas")
            .document(toId)
            .collection(fromId)
            .add(message)
            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Log.d("Teste: ", documentReference.getId());
                }
            })
             .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("Erro: ", e.getMessage(), e);
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
    }
}
