package com.example.papyrus;

import java.util.UUID;

public class Livro {
    private final String id;
    private final String titulo;
    private final String autor;
    private final int ano;

    public Livro(String titulo, String autor, int ano) {
        this.id = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAno() {
        return ano;
    }

    public String getId() {
        return id;
    }
}