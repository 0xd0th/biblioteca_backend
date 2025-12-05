package com.unifor.biblioteca.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users") // "users" no plural evita conflito com palavras reservadas do SQL
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private int matricula;

    private String nome;
    private String sobrenome;
    private String curso;
    private String senha;

    public User() {}

    // Construtor utilitário
    public User(int matricula, String nome, String sobrenome, String curso, String senha) {
        this.matricula = matricula;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.curso = curso;
        this.senha = senha;
    }

    // Getters e Setters
    public Integer getId() { 
        return id; 
    }
    public void setId(Integer id) { 
        this.id = id; 
    }

    public int getMatricula() { 
        return matricula; 
    }
    public void setMatricula(int matricula) { 
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

    public String getSenha() { 
        return senha; 
    }
    public void setSenha(String senha) { 
        this.senha = senha; 
    }
}