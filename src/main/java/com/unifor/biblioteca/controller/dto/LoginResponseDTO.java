package com.unifor.biblioteca.controller.dto;

public class LoginResponseDTO {

    private String token;
    private String matricula;

    public LoginResponseDTO(String token, String matricula) {
        this.token = token;
        this.matricula = matricula;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
