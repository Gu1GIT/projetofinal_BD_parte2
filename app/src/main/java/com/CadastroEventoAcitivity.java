package com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.database.EventoDAO;
import com.database.LocalDAO;
import com.example.evento.R;
import com.modelo.Evento;
import com.modelo.Local;

import java.text.DateFormat;

public class CadastroEventoAcitivity extends AppCompatActivity {

    private boolean excluir = false;
    private int id = 0;
    private Spinner spinnerLocal;
    private ArrayAdapter<Local> localAdapter;
    private EditText editTextNome ;
    private EditText editTextData ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);
        setTitle("Cadastro de Eventos");
        spinnerLocal = findViewById(R.id.spinner_local);
        editTextNome = findViewById(R.id.editTextNome);
        editTextData = findViewById(R.id.editTextData);
        carregarLocal();
        carregarEvento();
    }

    private void carregarLocal () {
        LocalDAO localDAO = new LocalDAO(getBaseContext());
        localAdapter = new ArrayAdapter<Local>(CadastroEventoAcitivity.this,
                android.R.layout.simple_spinner_item,
                localDAO.Listar());
        spinnerLocal.setAdapter(localAdapter);
    }

    private void carregarEvento() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null &&
                intent.getExtras().get("eventotoSelecionado") != null) {
            Evento evento = (Evento) intent.getExtras().get("eventotoSelecionado");
            editTextNome.setText(evento.getNome());
            editTextData.setText(evento.getData().toString());
            int posicaoLocal = obterPosicaoLocal(evento.getLocal());
            spinnerLocal.setSelection(posicaoLocal);
            id = evento.getId();
        }
    }

    private int obterPosicaoLocal (Local local){
        for (int posicao =0 ; posicao<localAdapter.getCount(); posicao++){
            if (localAdapter.getItem(posicao).getId()==local.getId()){
                return posicao;
            }
        }
        return 0;
    }

    public void onClickBack(View v) {
        finish();
    }

    public void onClickSave(View v) {
        processar();

    }

    public void onClickRemove(View v) {
        excluir = true;
        processar();

    }

    private void processar() {


        String nome = editTextNome.getText().toString();
        String data = editTextData.getText().toString();
        int posicaoLocal = spinnerLocal.getSelectedItemPosition();
        Local local = (Local) localAdapter.getItem(posicaoLocal);

        if ((nome.isEmpty() || data.isEmpty()) && !excluir) {
            erroMensagem();
            return;

        } else {

            Evento evento = new Evento(id, nome, data, local);
            EventoDAO eventoDAO = new EventoDAO(getBaseContext());


            if (excluir) {
                eventoDAO.excluir(evento);
            } else {
                boolean salvou = eventoDAO.salvar(evento);
                if (!salvou) {
                    Toast.makeText(CadastroEventoAcitivity.this,
                            "Erro ao salvar",
                            Toast.LENGTH_LONG).show();
                }
            }
            finish();
        }
    }

    private void erroMensagem() {
        Toast.makeText(CadastroEventoAcitivity.this, "É obrigatório preencher todos os campos", Toast.LENGTH_LONG).show();
    }
}