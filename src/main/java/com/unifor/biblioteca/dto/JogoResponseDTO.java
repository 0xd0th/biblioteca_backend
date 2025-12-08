package com.unifor.biblioteca.dto;

public class JogoResponseDTO {

    private int id;
    private String titulo;
    private int maxJogadores;
    private int minJogadores;
    private String editora;
    private String genero;
    private String status;

    public JogoResponseDTO(int id, String titulo, int maxJogadores, int minJogadores, String editora, String genero, String status) {
        this.id = id;
        this.titulo = titulo;
        this.maxJogadores = maxJogadores;
        this.minJogadores = minJogadores;
        this.editora = editora;
        this.genero = genero;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getMaxJogadores() {
        return maxJogadores;
    }

    public void setMaxJogadores(int maxJogadores) {
        this.maxJogadores = maxJogadores;
    }

    public int getMinJogadores() {
        return minJogadores;
    }

    public void setMinJogadores(int minJogadores) {
        this.minJogadores = minJogadores;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
