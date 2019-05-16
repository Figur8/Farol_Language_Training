package com.slumdev.farol;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.slumdev.farol.classes.Message;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<Message> {
    private List<Message> mensagens;
    private Activity contexto;

    public MessageAdapter(Activity context, List<Message> mensagens) {
        super(context, android.R.layout.simple_list_item_1 ,mensagens);
        this.contexto = context;
        this.mensagens = mensagens;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = contexto.getLayoutInflater();
        TextView text = null;
        Message message = mensagens.get(position);

        convertView = inflater.inflate(R.layout.adpt_model_reciver_msg,parent,false);
        text = convertView.findViewById(R.id.mensagem);

        // Testando qual usuário fez o login no app e modificando o layout de acordo
        if(message.getUserId().equals("enviador")){
            convertView = inflater.inflate(R.layout.adpt_model_send_msg,parent,false);
            text = convertView.findViewById(R.id.mensagem);
            text.setText(message.getTexto());
        }
        if(message.getTexto().isEmpty()){
            convertView = inflater.inflate(R.layout.adpt_model_reciver_msg,parent,false);
            text = convertView.findViewById(R.id.mensagem);
            text.setText("Mensagem recebida");
        }

        return convertView;
    }

}

//TODO - Verificar usuário autenticado para definir qual layout será usado para enviar as msgs.

