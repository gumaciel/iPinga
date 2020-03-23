package com.ipinga.bmc.ipinga;

public class Loja {
    private String nome;
    private int imagem;
    private String endereco;
    public Loja (String nome, String endereco, int imagem) {
        this.nome = nome;
        this.endereco = endereco;
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }
    
    //tag 3

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}