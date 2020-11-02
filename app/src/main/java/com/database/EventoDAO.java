package com.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.database.entity.EventoEntity;
import com.database.entity.LocalEntity;
import com.modelo.Evento;
import com.modelo.Local;

import java.util.ArrayList;
import java.util.List;

public class EventoDAO {
    private final String SQL_LISTAR_TODOS = "SELECT Evento._id, nome, data, idlocal, " +
            "nome_local, bairro, cidade, capacidade_de_publico FROM " +
            EventoEntity.TABLE_NAME +
            " INNER JOIN " + LocalEntity.TABLE_NAME + " ON " +
            EventoEntity.COLUMN_NAME_ID_LOCAL +
            " = " + LocalEntity.TABLE_NAME +"."+ LocalEntity._ID;
    private DBGateway dbGateway;


    public EventoDAO(Context context){
        dbGateway=DBGateway.getInstance(context);
    }

    public boolean salvar(Evento evento) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(EventoEntity.COLUMN_NAME_NOME, evento.getNome());
        contentValues.put(EventoEntity.COLUMN_NAME_DATA, evento.getData());
        contentValues.put(EventoEntity.COLUMN_NAME_ID_LOCAL, evento.getLocal().getId());
        if (evento.getId() > 0) {
            return dbGateway.getDatabase().update(EventoEntity.TABLE_NAME, contentValues,
                    EventoEntity._ID + "=?",
                    new String[]{String.valueOf(evento.getId())}) > 0;
        }
        return dbGateway.getDatabase().insert(EventoEntity.TABLE_NAME,
                null, contentValues) > 0;
    }

    public boolean excluir(Evento evento) {
        return dbGateway.getDatabase().delete(EventoEntity.TABLE_NAME,
                EventoEntity._ID + "=?",
                new String[]{String.valueOf(evento.getId())}) > 0;

    }


    public List<Evento> Listar() {
        List<Evento> eventos = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LISTAR_TODOS, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(EventoEntity._ID));
            String nome = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_NOME));
            String data = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_DATA));
            int idlocal = cursor.getInt(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_ID_LOCAL));
            String nome_local = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_NOME_LOCAL));
            String bairro = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_BAIRRO));
            String cidade = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CIDADE));
            String capacidade_de_publico = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CAPACIDADE_DE_PUBLICO));
            Float capacidadefloat = Float.parseFloat(capacidade_de_publico);
            Local local = new Local(idlocal, nome_local, bairro, cidade, capacidadefloat);
            eventos.add(new Evento(id, nome, data, local));
        }
        cursor.close();
        return eventos;

    }
}
