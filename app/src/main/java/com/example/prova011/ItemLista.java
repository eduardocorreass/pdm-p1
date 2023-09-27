package com.example.prova011;

public class ItemLista {
    private int id;
    private String texto;
    private int cor;

    public ItemLista(int id, String texto, int cor) {
        this.id = id;
        this.texto = texto;
        this.cor = cor;
    }

    public ItemLista(String texto, int cor) {
        this.texto = texto;
        this.cor = cor;
    }

    public int getId(){
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public int getCor() {
        return cor;
    }
}
