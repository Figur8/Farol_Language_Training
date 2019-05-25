package com.slumdev.farol;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.slumdev.farol.classes.Contacts;
import com.slumdev.farol.classes.Message;
import com.slumdev.farol.classes.User;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class ChatActivity extends AppCompatActivity{

    private FirebaseFirestore colletctionConversas = FirebaseFirestore.getInstance();
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private User user = new User();
    private User me = new User();


    private ListView lista;
    private EditText sendMsg;
//    private Button btnSendMsg;
    private List<Message> listMsg = new ArrayList<>();
    private Message message;
    private MessageAdapter mAdpater;

    /*View.OnClickListener send = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            message = new Message();
            message.setTexto(sendMsg.getText().toString());

            // Atribuindo o valor do Id do usuário para testes de layout
            message.setUserId("enviador");

            mAdpater = new MessageAdapter(ChatActivity.this, listMsg);
            listMsg.add(message);
            lista.setAdapter(mAdpater);
            sendMsg.setText("");
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


//        sendMsg = findViewById(R.id.edit_send_msg);
//        lista = findViewById(R.id.list_chat);
//        btnSendMsg = findViewById(R.id.btn_send_msg);
//        btnSendMsg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sendMessage();
//            }
//        });
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
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Método para procurar as mensagens na coleção
    private void fetchMessage(){
        if(me != null){
            // Aqui eu defino quem é quem nas mensagens
            String fromId = me.getUuid();
            String toId = user.getUuid();

            colletctionConversas.collection("/conversas")
                .document(fromId)
                .collection(toId)
                // Ordenando conversas por período ascendente
                .orderBy("timestamp", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                    //Crio uma lista para armazenar a coleção
                    List<DocumentChange> documentChanges = queryDocumentSnapshots.getDocumentChanges();
                    // Verifico se o documento está vazio e para cada modficação no registro eu armazeno o objeto atualizado
                    if(documentChanges != null){
                        for (DocumentChange doc : documentChanges ) {
                            if(doc.getType() == DocumentChange.Type.ADDED){
                                Message message = doc.getDocument().toObject(Message.class);
                                // TODO - Adaptar uma lista para exibir os registros
                                List<String> msg = new ArrayList<>();
                                msg.add(message.getTexto());
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ChatActivity.this, R.layout.adpt_model_send_msg, msg);
                                lista.setAdapter(adapter);

                            }
                        }
                    }
                    }
                });
        }
    }

    // Aqui a mágica acontece...
    private void sendMessage(){
        String texto = sendMsg.getText().toString();

        final String fromId = auth.getUid();
        final String toId = user.getUuid();
        long timestamp = System.currentTimeMillis();
        //Corpo da mensagem
        final Message messageFrom = new Message();
        messageFrom.setFromId(fromId);
        messageFrom.setToId(toId);
        messageFrom.setTimestamp(timestamp);
        messageFrom.setTexto(texto);

        //Salvando as conversas na coleção do firestore
        if(!messageFrom.getTexto().isEmpty()){
            colletctionConversas.collection("/conversas")
                    .document(fromId)
                    .collection(toId)
                    .add(messageFrom)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("Teste: ", documentReference.getId());

                            //Crio um novo contato pra receber as mensagens
                            Contacts contacts = new Contacts();
                            contacts.setUuid(toId);
                            contacts.setUsername(user.getUsername());
//                            contacts.setPhotoUrl(me.getProfileUrl());
                            contacts.setTimestamp(messageFrom.getTimestamp());
                            contacts.setLastMessage(messageFrom.getTexto());
                            colletctionConversas.collection("/last-message")
                                    .document(fromId)
                                    .collection("contacts")
                                    .document(toId)
                                    .set(contacts);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("Teste: ", e.getMessage(), e);
                }
            });

            //Recebendo a mensagem do outro lado
            colletctionConversas.collection("/conversas")
                    .document(toId)
                    .collection(fromId)
                    .add(messageFrom)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("Teste: ", documentReference.getId());

                            Contacts contacts = new Contacts();
                            contacts.setUuid(fromId);
                            contacts.setUsername(me.getUsername());
//                            contacts.setPhotoUrl(user.getProfileUrl());
                            contacts.setTimestamp(messageFrom.getTimestamp());
                            contacts.setLastMessage(messageFrom.getTexto());
                            colletctionConversas.collection("/last-message")
                                    .document(toId)
                                    .collection("contacts")
                                    .document(fromId)
                                    .set(contacts);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("Erro: ", e.getMessage(), e);
                }
            });
        }
    }

    //TODO - criar uma classe interna que possa controlar os atributos das mensagens
    //TODO - falta acertar o envio e recebimento das mensagens
}
