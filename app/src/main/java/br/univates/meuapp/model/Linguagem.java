package br.univates.meuapp.model;

import java.util.Date;

public class Linguagem {

    private int id;
    private String nome;
    private String cargo;
    private int favorito;
    private int nota;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getFavorito() {
        return favorito;
    }


    public void setFavorito(int favorito) {
        this.favorito = favorito;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    @Override
    public String toString(){
        return nome;
    }
}
