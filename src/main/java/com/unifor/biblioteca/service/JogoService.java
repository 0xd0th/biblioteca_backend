package com.unifor.biblioteca.service;

import com.unifor.biblioteca.controller.dto.JogoResponseDTO;
import com.unifor.biblioteca.data.model.Jogo;
import com.unifor.biblioteca.data.repository.JogoRepository;
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

    public List<JogoResponseDTO> pegarTodos() {
        return jogoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public JogoResponseDTO pegarPorId(int id) {
        Optional<Jogo> jogo = jogoRepository.findById(id);
        return jogo.map(this::toDTO).orElse(null);
    }

    public void cadastrar(JogoResponseDTO jogoDTO) {
        Jogo jogo = toEntity(jogoDTO);
        jogoRepository.save(jogo);
    }


    private JogoResponseDTO toDTO(Jogo jogo) {
        return new JogoResponseDTO(jogo.getId(), jogo.getTitulo(), jogo.getMaxJogadores(), jogo.getMinJogadores(),
                jogo.getEditora(), jogo.getGenero(), jogo.getStatus());
    }

    private Jogo toEntity(JogoResponseDTO dto) {
        Jogo jogo = new Jogo();
        jogo.setTitulo(dto.getTitulo());
        jogo.setStatus(dto.getStatus());
        return jogo;
    }
}