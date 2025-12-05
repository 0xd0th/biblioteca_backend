package com.unifor.biblioteca.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "estudante_has_livro")
public class EmprestimoLivro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Relacionamento com o USUÁRIO (Aluno)
    @ManyToOne
    @JoinColumn(name = "user_id") 
    private User user;

    // Relacionamento com o LIVRO
    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;
    
    private String status; // Ex: "ATIVO", "FINALIZADO", "ATRASADO"

    public EmprestimoLivro() {}

    public EmprestimoLivro(User user, Livro livro, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista) {
        this.user = user;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.status = "ATIVO";
    }

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

    public Livro getLivro() { 
        return livro; 
    }
    public void setLivro(Livro livro) { 
        this.livro = livro; 
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