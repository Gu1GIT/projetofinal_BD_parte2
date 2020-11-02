package com.modelo;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Local implements Serializable {

    private int id;
    private String nome_local;
    private String  bairro;
    private String cidade;
    private Float capacidade_de_publico;


    public Local(int id, String nome_local, String bairro,
                 String cidade, Float capacidade_de_publico) {
        this.id = id;
        this.nome_local = nome_local;
        this.bairro = bairro;
        this.cidade = cidade;
        this.capacidade_de_publico = capacidade_de_publico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_local() {
        return nome_local;
    }

    public void setNome_local(String nome_local) {
        this.nome_local = nome_local;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Float getCapacidade_de_publico() {
        return capacidade_de_publico;
    }

    public void setCapacidade_de_publico(Float capacidade_de_publico) {
        this.capacidade_de_publico = capacidade_de_publico;
    }

    @NonNull
    @Override
    public String toString() {

        return ("Local: "+ nome_local+ "\n" +"Bairro: " + bairro + "\n" + "Cidade: " + cidade + "\n" + "Capacidade: " + capacidade_de_publico);
    }
}
