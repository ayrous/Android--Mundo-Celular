package com.example.acer.mundocelular.model;

import java.io.Serializable;

public class Mensagem implements Serializable {

    private Long id;
    private String mensagem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String toString() {
        return getId() + " - " + getMensagem();
    }
}