package com;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;


import com.database.LocalDAO;
import com.example.evento.R;

import com.modelo.Local;

public class ListarLocalActivity extends AppCompatActivity {


    private ListView listViewLocal;
    private ArrayAdapter<Local> adapterLocal;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_local);
        setTitle("Eventos");
        listViewLocal = findViewById(R.id.listView_local);
        onClickListaLocal();
    }

    @Override
    protected void onResume() {
        super.onResume();

        LocalDAO localDAO = new LocalDAO(getApplicationContext());
        adapterLocal = new ArrayAdapter<Local>(ListarLocalActivity.this,
                android.R.layout.simple_list_item_1,
                localDAO.Listar());
        listViewLocal.setAdapter(adapterLocal);
    }

    private void onClickListaLocal() {
        listViewLocal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Local localSelecionado = adapterLocal.getItem(position);
                Intent intent = new Intent(ListarLocalActivity.this, CadastroLocalAcitivity.class);
                intent.putExtra("localSelecionado", localSelecionado);
                startActivity(intent);
            }
        });
    }


    public void onClickNew(View v) {
        Intent intent = new Intent(ListarLocalActivity.this,
                CadastroLocalAcitivity.class);
        startActivity(intent);
    }

    public void onClickEventos (View v){
        Intent intent = new Intent(ListarLocalActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}