package com;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.database.LocalDAO;
import com.example.evento.R;

import com.modelo.Local;

public class CadastroLocalAcitivity extends AppCompatActivity {

    private boolean excluir = false;
    private int id = 0;
    private EditText editTextNomeLocal;
    private EditText editTextBairro;
    private EditText editTextCidade;
    private EditText editTextCapacidade;
    private Float capacidadefloat = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_local);
        setTitle("Cadastro de Local");
        editTextNomeLocal = findViewById(R.id.editTextNomeLocal);
        editTextBairro = findViewById(R.id.editTextBairro);
        editTextCidade = findViewById(R.id.editTextLCidade);
        editTextCapacidade = findViewById(R.id.editTextLCapacidade);
        carregarLocal();
    }

    private void carregarLocal() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null &&
                intent.getExtras().get("localSelecionado") != null) {
            Local local = (Local) intent.getExtras().get("localSelecionado");
            editTextNomeLocal.setText(local.getNome_local());
            editTextBairro.setText(local.getBairro());
            editTextCidade.setText(local.getCidade());
            editTextCapacidade.setText(local.getCapacidade_de_publico().toString());
            id = local.getId();
        }
        
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


        String nomelocal = editTextNomeLocal.getText().toString();
        String bairro = editTextBairro.getText().toString();
        String cidade = editTextCidade.getText().toString();
        String capacidade =  editTextCapacidade.getText().toString();

        if (!capacidade.isEmpty()) {
            capacidadefloat = Float.parseFloat(capacidade);
        }


        if ((nomelocal.isEmpty() || bairro.isEmpty() || cidade.isEmpty()|| capacidade.isEmpty()) && !excluir) {
            erroMensagem();
            return;

        } else {

            Local local = new Local (id, nomelocal, bairro, cidade, capacidadefloat);
            LocalDAO localDAO = new LocalDAO(getBaseContext());


            if (excluir) {
                localDAO.excluir(local);
            } else {
                boolean salvou = localDAO.salvar(local);
                if (!salvou) {
                    Toast.makeText(CadastroLocalAcitivity.this,
                            "Erro ao salvar",
                            Toast.LENGTH_LONG).show();
                }
            }
            finish();
        }
    }

    private void erroMensagem() {
        Toast.makeText(CadastroLocalAcitivity.this, "É obrigatório preencher todos os campos", Toast.LENGTH_LONG).show();
    }

}