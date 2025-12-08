package com.unifor.biblioteca.data.model;

import jakarta.persistence.*;

@Entity
@Table(name = "jogos")
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;
    
    private String editora; // Ex: Galápagos, Devir (Substitui "Desenvolvedora")
    
    private String genero;  // Ex: Estratégia, Party Game, Euro
    
    @Column(name = "min_jogadores")
    private int minJogadores; // Ex: 2
    
    @Column(name = "max_jogadores")
    private int maxJogadores; // Ex: 6
    
    private String status; // Ex: DISPONIVEL, EMPRESTADO, MANUTENCAO

    public Jogo() {}

    public Jogo(String titulo, String editora, String genero, int minJogadores, int maxJogadores, String status) {
        this.titulo = titulo;
        this.editora = editora;
        this.genero = genero;
        this.minJogadores = minJogadores;
        this.maxJogadores = maxJogadores;
        this.status = status;
    }

    // --- Getters e Setters ---

    public Integer getId() { 
        return id; 
    }
    public void setId(Integer id) {     
        this.id = id; 
    }

    public String getTitulo() { 
        return titulo; 
    }
    public void setTitulo(String titulo) { 
        this.titulo = titulo; 
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

    public int getMinJogadores() { 
        return minJogadores; 
    }
    public void setMinJogadores(int minJogadores) { 
        this.minJogadores = minJogadores; 
    }

    public int getMaxJogadores() { 
        return maxJogadores; 
    }
    public void setMaxJogadores(int maxJogadores) { 
        this.maxJogadores = maxJogadores; 
    }

    public String getStatus() { 
        return status; 
    }
    public void setStatus(String status) {
        this.status = status; 
    }
}