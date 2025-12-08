package com.unifor.biblioteca.controller.dto;

public class EmprestimoRequestDTO {
    private String matricula;
    private int idItem;

    // Getters e Setters
    public String getMatricula() {
        return matricula; 
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula; 
    }

    public Integer getIdItem() { 
        return idItem; 
    }
    public void setIdItem(Integer idItem) { 
        this.idItem = idItem; 
    }
}