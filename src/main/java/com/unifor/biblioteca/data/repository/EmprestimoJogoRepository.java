package com.unifor.biblioteca.data.repository;

import com.unifor.biblioteca.data.model.EmprestimoJogo;
import com.unifor.biblioteca.data.model.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmprestimoJogoRepository extends JpaRepository<EmprestimoJogo, Integer> {
    Optional<EmprestimoJogo> findByJogoAndStatus(Jogo jogo, String status);
}