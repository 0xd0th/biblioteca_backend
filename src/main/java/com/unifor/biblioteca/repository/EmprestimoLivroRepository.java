package com.unifor.biblioteca.repository;

import com.unifor.biblioteca.model.EmprestimoLivro;
import com.unifor.biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmprestimoLivroRepository extends JpaRepository<EmprestimoLivro, Integer> {
    // Busca um empréstimo desse livro que ainda esteja com status ATIVO
    Optional<EmprestimoLivro> findByLivroAndStatus(Livro livro, String status);
}