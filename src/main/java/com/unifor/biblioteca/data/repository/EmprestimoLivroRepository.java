package com.unifor.biblioteca.data.repository;

import com.unifor.biblioteca.data.model.EmprestimoLivro;
import com.unifor.biblioteca.data.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmprestimoLivroRepository extends JpaRepository<EmprestimoLivro, Integer> {
    Optional<EmprestimoLivro> findByLivroAndStatus(Livro livro, String status);
}