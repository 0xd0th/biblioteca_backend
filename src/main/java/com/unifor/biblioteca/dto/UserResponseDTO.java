package com.unifor.biblioteca.dto;

public class UserResponseDTO {

    public String matricula;
    public String nome;
    public String sobrenome;
    public String curso;

    public UserResponseDTO(String matricula, String nome, String sobrenome, String curso) {
        this.matricula = matricula;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.curso = curso;
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

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
}
