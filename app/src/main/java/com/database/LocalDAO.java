package com.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


import com.database.entity.LocalEntity;
import com.modelo.Local;

import java.util.ArrayList;
import java.util.List;

public class LocalDAO {
    private final String SQL_LISTAR_TODOS = "SELECT * FROM " +
            LocalEntity.TABLE_NAME;
    private DBGateway dbGateway;


    public LocalDAO(Context context){
        dbGateway=DBGateway.getInstance(context);
    }

    public boolean salvar(Local local) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(LocalEntity.COLUMN_NAME_NOME_LOCAL, local.getNome_local());
        contentValues.put(LocalEntity.COLUMN_NAME_BAIRRO, local.getBairro());
        contentValues.put(LocalEntity.COLUMN_NAME_CIDADE, local.getCidade());
        contentValues.put(LocalEntity.COLUMN_NAME_CAPACIDADE_DE_PUBLICO,
                local.getCapacidade_de_publico());
        if (local.getId() > 0) {
            return dbGateway.getDatabase().update(LocalEntity.TABLE_NAME, contentValues,
                    LocalEntity._ID + "=?",
                    new String[]{String.valueOf(local.getId())}) > 0;
        }
        return dbGateway.getDatabase().insert(LocalEntity.TABLE_NAME,
                null, contentValues) > 0;
    }

    public boolean excluir(Local local) {
        return dbGateway.getDatabase().delete(LocalEntity.TABLE_NAME,
                LocalEntity._ID + "=?",
                new String[]{String.valueOf(local.getId())}) > 0;

    }


    public List<Local> Listar() {
        List<Local> local = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LISTAR_TODOS, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(LocalEntity._ID));
            String nome_local = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_NOME_LOCAL));
            String bairro = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_BAIRRO));
            String cidade = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CIDADE));
            Float capacidade_de_publico = cursor.getFloat(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CAPACIDADE_DE_PUBLICO));
            local.add(new Local(id, nome_local, bairro, cidade, capacidade_de_publico));
        }
        cursor.close();
        return local;

    }
}
