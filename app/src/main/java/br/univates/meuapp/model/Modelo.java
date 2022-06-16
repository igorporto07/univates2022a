package br.univates.meuapp.model;

public class Modelo {

    private int id;
    private String nome;
    private int id_marca;

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

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    @Override
    public String toString(){
        return nome;
    }
}
