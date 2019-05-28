package com.slumdev.farol;

import android.app.NotificationManager;
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
import java.util.List;
import javax.annotation.Nullable;

public class ChatActivity extends AppCompatActivity {

    // Firebase
    private FirebaseFirestore colletctionConversas = FirebaseFirestore.getInstance();
    private FirebaseFirestore collectionUsers = FirebaseFirestore.getInstance();
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    // Elementos da View
    private EditText editdMsg;
    private Button btnSendMsg;

    // RecyclerView
    private GroupAdapter adapter = new GroupAdapter();
    private RecyclerView rvMessage;

    // Usuários
    private User userContact = new User();
    private User eumesmo = new User();
    private LinearLayoutManager lchat = new LinearLayoutManager(ChatActivity.this);
    private int count = 0;


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

        userContact = (User) getIntent().getExtras().get("userContact"); // aqui recebo dos contatos
        getSupportActionBar().setTitle(userContact.getUsername());


        // TODO - tentar enviar somente um usuário... assim a notificação funciona

        // Elementos da View
        btnSendMsg = findViewById(R.id.btn_send_msg);
        btnSendMsg.setOnClickListener(send);
        editdMsg = findViewById(R.id.edit_send_msg);

        // RecyclerView
        rvMessage = findViewById(R.id.rv_message_chat);
        rvMessage.setLayoutManager(lchat);
        rvMessage.setAdapter(adapter);
        lchat.setReverseLayout(true);
        rvMessage.scrollToPosition(count);

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
        if (eumesmo != null) {
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
                        if (documentChanges != null) {
                            for (DocumentChange doc : documentChanges) {
                                // getType me diz se o tipo do documento é ADDED, ou seja, objeto adicionado recente
                                if (doc.getType() == DocumentChange.Type.ADDED) {
                                    Message message = doc.getDocument().toObject(Message.class);
                                    adapter.add(new MessageItem(message));
                                    Log.d("Contagem: ", message.getTexto());
                                }
                            }
                        }
                    }
                });
        }
    }

    private class MessageItem extends Item<ViewHolder> {

        private final Message message;

        private MessageItem(Message message) {
            this.message = message;
        }

        @Override
        public void bind(@NonNull ViewHolder viewHolder, int position) {
            TextView msg = viewHolder.itemView.findViewById(R.id.mensagem);
            ImageView img = viewHolder.itemView.findViewById(R.id.imagemMsg);
            msg.setText(message.getTexto());
            if(getLayout() == R.layout.item_from_msg){
                Picasso.get().load(userContact.getProfileUrl()).into(img);
            }else if(getLayout() == R.layout.item_to_msg){
                Picasso.get().load(eumesmo.getProfileUrl()).into(img);
            }

        }

        @Override
        public int getLayout() {
            return message.getFromId().equals(auth.getUid()) ? R.layout.item_to_msg : R.layout.item_from_msg;
        }
    }
    // Criando o menu da ActionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_chat, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.volta:
                finish();
                Intent c = new Intent(ChatActivity.this, MainActivity.class);
                startActivity(c);
                return true;
            case R.id.feedback:
                Intent f = new Intent(ChatActivity.this, FeedBackActivity.class);
                startActivity(f);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Aqui a mágica acontece...
    private void sendMessage() {
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
        if (!message.getTexto().isEmpty()) {
            colletctionConversas.collection("/conversas")
                    .document(fromId)
                    .collection(toId)
                    .add(message)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("Teste: ", documentReference.getId());
                            Contacts contato = new Contacts();
                            contato.setUuid(toId);
                            contato.setUsername(userContact.getUsername());
                            contato.setPhotoUrl(eumesmo.getProfileUrl());
                            contato.setTimestamp(message.getTimestamp());
                            contato.setLastMessage(message.getTexto());
                            colletctionConversas.collection("/last-messages")
                                    .document(fromId)
                                    .collection("contatos")
                                    .document(toId)
                                    .set(contato);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("Teste: ", e.getMessage(), e);
                }
            });
            //Salvando as conversas na coleção recebendo do outro lado
            colletctionConversas.collection("/conversas")
                    .document(toId)
                    .collection(fromId)
                    .add(message)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("Teste: ", documentReference.getId());
                            Contacts contato = new Contacts();
                            contato.setUuid(fromId);
                            contato.setUsername(eumesmo.getUsername());
                            contato.setPhotoUrl(userContact.getProfileUrl());
                            contato.setTimestamp(message.getTimestamp());
                            contato.setLastMessage(message.getTexto());
                            colletctionConversas.collection("/last-messages")
                                    .document(toId)
                                    .collection("contatos")
                                    .document(fromId)
                                    .set(contato);
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


// TODO - colocar setinha no botão de enviar