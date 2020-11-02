package  com.database.contract;

import com.database.entity.EventoEntity;
import com.database.entity.LocalEntity;
import com.modelo.Evento;

public final class EventoContract {
    private EventoContract() {}

    public static final String criarTabela() {
        return "CREATE TABLE " + EventoEntity.TABLE_NAME + " (" +
                EventoEntity._ID + " INTEGER PRIMARY KEY," +
                EventoEntity.COLUMN_NAME_NOME + " TEXT," +
                EventoEntity.COLUMN_NAME_DATA + " TEXT," +
                EventoEntity.COLUMN_NAME_ID_LOCAL + " INTEGER," +
                "FOREIGN KEY (" + EventoEntity.COLUMN_NAME_ID_LOCAL + ") REFERENCES "+
                EventoEntity.TABLE_NAME + "(" + EventoEntity._ID + "))";

    }
    public static final String removerTabela(){
       return "DROP TABLE IF EXISTS " + EventoEntity.TABLE_NAME;

    }
}
