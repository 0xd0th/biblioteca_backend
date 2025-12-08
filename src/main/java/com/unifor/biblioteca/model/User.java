package com.unifor.biblioteca.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users") // "users" no plural evita conflito com palavras reservadas do SQL
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String matricula;

    private String nome;
    private String sobrenome;
    private String curso;
    private String senhaCodificada;

    public User() {}

    // Construtor utilitário
    public User(String matricula, String nome, String sobrenome, String curso, String senhaCodificada) {
        this.matricula = matricula;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.curso = curso;
        this.senhaCodificada = senhaCodificada;
    }

    // Getters e Setters
    public Integer getId() { 
        return id; 
    }
    public void setId(Integer id) { 
        this.id = id; 
    }

    public String getMatricula() {
        return matricula; 
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula; 
    }

    public String getNome() { 
        return nome; 
    }
    public void setNome(String nome) { 
        this.nome = nome; 
    }

    public String getSobrenome() { 
        return sobrenome; 
    }
    public void setSobrenome(String sobrenome) { 
        this.sobrenome = sobrenome; 
    }

    public String getCurso() { 
        return curso; 
    }
    public void setCurso(String curso) { 
        this.curso = curso; 
    }

    public String getSenhaCodificada() {
        return senhaCodificada;
    }
    public void setSenhaCodificada(String senhaCodificada) {
        this.senhaCodificada = senhaCodificada;
    }
}