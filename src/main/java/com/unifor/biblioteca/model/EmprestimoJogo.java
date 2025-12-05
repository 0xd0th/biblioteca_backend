package com.unifor.biblioteca.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "estudante_has_jogo")
public class EmprestimoJogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "jogo_id")
    private Jogo jogo;

    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    
    // --- AQUI ESTÁ A VARIÁVEL QUE FALTAVA ---
    private LocalDate dataDevolucaoReal; 
    
    private String status;

    public EmprestimoJogo() {}

    public EmprestimoJogo(User user, Jogo jogo, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista) {
        this.user = user;
        this.jogo = jogo;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.status = "ATIVO";
    }

    // Getters e Setters
    public Integer getId() { 
        return id; 
    }
    public void setId(Integer id) { 
        this.id = id; 
    }

    public User getUser() { 
        return user; 
    }
    public void setUser(User user) { 
        this.user = user; 
    }

    public Jogo getJogo() { 
        return jogo; 
    }
    public void setJogo(Jogo jogo) { 
        this.jogo = jogo; 
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo; 
    }
    public void setDataEmprestimo(LocalDate dataEmprestimo) { 
        this.dataEmprestimo = dataEmprestimo; 
    }
    
    public LocalDate getDataDevolucaoPrevista() { 
        return dataDevolucaoPrevista; 
    }
    public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) { 
        this.dataDevolucaoPrevista = dataDevolucaoPrevista; 
    }

    public LocalDate getDataDevolucaoReal() { 
        return dataDevolucaoReal; 
    }
    public void setDataDevolucaoReal(LocalDate dataDevolucaoReal) { 
        this.dataDevolucaoReal = dataDevolucaoReal; 
    }

    public String getStatus() { 
        return status; 
    }
    public void setStatus(String status) { 
        this.status = status; 
    }
}