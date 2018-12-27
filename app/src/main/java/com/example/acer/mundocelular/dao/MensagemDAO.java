package com.example.acer.mundocelular.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.acer.mundocelular.model.Mensagem;

import java.util.ArrayList;
import java.util.List;

public class MensagemDAO extends SQLiteOpenHelper {
    public MensagemDAO(Context context) {
        super(context, "MensagensMundoCelular", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE mensagem(id INTEGER PRIMARY KEY, msg TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS mensagem;";
        db.execSQL(sql);
    }

    public void inserir(Mensagem mensagem) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValues(mensagem);

        db.insert("mensagem",null,dados);
    }

    @NonNull
    private ContentValues getContentValues(Mensagem mensagem) {
        ContentValues dados = new ContentValues();

        dados.put("msg", mensagem.getMensagem());
        return dados;
    }
    public List<Mensagem> buscaMensagem() {
        String sql = "SELECT * FROM mensagem;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        List<Mensagem> mensagens = new ArrayList<Mensagem>();
        while (c.moveToNext()){
            Mensagem mensagem = new Mensagem();
            mensagem.setId(c.getLong(c.getColumnIndex("id")));
            mensagem.setMensagem(c.getString(c.getColumnIndex("msg")));
            mensagens.add(mensagem);
        }
        c.close();
        return mensagens;
    }

    public void deletar(Mensagem mensagem) {
        SQLiteDatabase db = getWritableDatabase();
        String[] parametros = {String.valueOf(mensagem.getId())};
        db.delete("mensagem","id = ?", parametros);
    }

    public void alterar(Mensagem mensagem) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValues(mensagem);
        String[] parametros = {mensagem.getId().toString()};
        db.update("mensagem",dados,"id = ?", parametros);
    }

    public Mensagem localizar(Long mensagemId) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM mensagem WHERE id = ?";
        Cursor c =  db.rawQuery(sql, new String[]{String.valueOf(mensagemId)});

        c.moveToFirst();
        Mensagem mensagemRetornada = new Mensagem();
        mensagemRetornada.setId(c.getLong(c.getColumnIndex("id")));
        mensagemRetornada.setMensagem(c.getString(c.getColumnIndex("msg")));
        db.close();
        return mensagemRetornada;
    }
}

