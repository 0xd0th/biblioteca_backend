package com.unifor.biblioteca.repository;

import com.unifor.biblioteca.model.EmprestimoJogo;
import com.unifor.biblioteca.model.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmprestimoJogoRepository extends JpaRepository<EmprestimoJogo, Integer> {
    Optional<EmprestimoJogo> findByJogoAndStatus(Jogo jogo, String status);
}