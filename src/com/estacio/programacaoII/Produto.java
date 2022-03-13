package com.estacio.programacaoII;

public class Produto {

    public Produto(String nome, float precoDeEtiqueta) {
        this.nome = nome;
        this.precoDeEtiqueta = precoDeEtiqueta;
    }

    private String nome;

    public String getNome() {
        return nome;
    }

    private double precoDeEtiqueta;

    public double getPrecoDeEtiqueta() {
        return precoDeEtiqueta;
    }
}
