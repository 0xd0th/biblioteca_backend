package com.unifor.biblioteca.service;

import com.unifor.biblioteca.dto.JogoDTO;
import com.unifor.biblioteca.model.Jogo;
import com.unifor.biblioteca.repository.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JogoService {

    @Autowired
    private JogoRepository jogoRepository;

    public JogoService() {}

    public List<JogoDTO> pegarTodos() {
        return jogoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public JogoDTO pegarPorId(int id) {
        Optional<Jogo> jogo = jogoRepository.findById(id);
        return jogo.map(this::toDTO).orElse(null);
    }

    public void cadastrar(JogoDTO jogoDTO) {
        Jogo jogo = toEntity(jogoDTO);
        jogoRepository.save(jogo);
    }

    // --- Conversores ---

    private JogoDTO toDTO(Jogo jogo) {
        return new JogoDTO(jogo.getId(), jogo.getTitulo(), jogo.getStatus()); 
    }

    private Jogo toEntity(JogoDTO dto) {
        Jogo jogo = new Jogo();
        jogo.setTitulo(dto.getTitulo());
        jogo.setStatus(dto.getStatus());
        return jogo;
    }
}