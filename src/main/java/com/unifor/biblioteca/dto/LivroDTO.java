package com.unifor.biblioteca.dto;

public class LivroDTO {

    private Integer id;
    private String titulo;
    private String autor;
    private int ano;
    private String status;

    public LivroDTO ( Integer id, String titulo, String autor, int ano, String status ) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.status = status;
    }


    public Integer getId() {
        return id;
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

    public String getStatus() {
        return status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("ID: %d\nTitulo: %s\nAutor: %s\nAno: %d\nStatus: %s\n",
                id, titulo, autor, ano, status);
    }

}

