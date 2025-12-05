package com.unifor.biblioteca.dto;

public class EmprestimoRequestDTO {
    private int matricula;
    private int idItem; // Pode ser ID do livro ou do jogo

    // Getters e Setters
    public Integer getMatricula() { 
        return matricula; 
    }
    public void setMatricula(Integer matricula) { 
        this.matricula = matricula; 
    }

    public Integer getIdItem() { 
        return idItem; 
    }
    public void setIdItem(Integer idItem) { 
        this.idItem = idItem; 
    }
}