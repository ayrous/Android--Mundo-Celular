package com.example.acer.mundocelular;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.acer.mundocelular.model.Mensagem;

public class MensagemHelper {

    public EditText msg;

    public Mensagem mensagem;

    public MensagemHelper(AvaliacaoActivity avaliacao){

        msg = avaliacao.findViewById(R.id.edTexto);
        mensagem = new Mensagem();
    }

    public Mensagem pegaMensgem() {

        mensagem.setMensagem(msg.getText().toString());
        return mensagem;
    }
    public void preecherAvaliacao(Mensagem mensagem) {
        msg.setText(mensagem.getMensagem());
        this.mensagem = mensagem;
    }
}
