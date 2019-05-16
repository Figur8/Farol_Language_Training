package com.slumdev.farol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.slumdev.farol.classes.Message;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity{

    private Toolbar toolbar;
    private ListView lista;
    private EditText sendMsg;
    private Button btnSendMsg;
    private List<Message> listMsg = new ArrayList<>();
    private Message message;
    private MessageAdapter mAdpater;

    View.OnClickListener send = new View.OnClickListener() {
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
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        sendMsg = findViewById(R.id.edit_send_msg);
        lista = findViewById(R.id.list_chat);
        btnSendMsg = findViewById(R.id.btn_send_msg);

        btnSendMsg.setOnClickListener(send);

        toolbar = findViewById(R.id.toolbar_chat);
        setSupportActionBar(toolbar);

    }

}
//TODO - Colocar botão de feedback na toolbar do chat.