package com.database.contract;

import com.database.entity.EventoEntity;
import com.database.entity.LocalEntity;

public final class LocalContract {
    private LocalContract() {}

    public static final String criarTabela() {
        return "CREATE TABLE " + LocalEntity.TABLE_NAME + " (" +
                LocalEntity._ID + " INTEGER PRIMARY KEY," +
                LocalEntity.COLUMN_NAME_NOME_LOCAL + " TEXT," +
                LocalEntity.COLUMN_NAME_BAIRRO + " TEXT," +
                LocalEntity.COLUMN_NAME_CIDADE + " TEXT," +
                LocalEntity.COLUMN_NAME_CAPACIDADE_DE_PUBLICO + " REAL)";


    }
    public static final String removerTabela(){
       return "DROP TABLE IF EXISTS " + LocalEntity.TABLE_NAME;

    }
}
