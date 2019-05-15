package com.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.models.Adpt;
import com.models.Mesages;
import com.pi4.farol.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

    private ListView list;
    private String[] msgs;
    private Button btnSendMsg;
    private EditText message;
    private Mesages msg = new Mesages();
    private Adpt adpt;

    public ChatFragment() {
        // Required empty public constructor
    }

    View.OnClickListener inserir = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            msg.setTexto(message.getText().toString());
            msgs = new String[]{msg.toString()};
            adpt = new Adpt(getActivity(), msgs);
            list.setAdapter(adpt);
            message.setText("");
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_chat, container, false);
            list = v.findViewById(R.id.lv_chat);
            btnSendMsg = v.findViewById(R.id.btn_send_msg);
            message = v.findViewById(R.id.editMsg);
            btnSendMsg.setOnClickListener(inserir);
        return v;
    }
    //TODO - Arrumar o envio da mensagem, prrencher e adicionar as mensagens

}
