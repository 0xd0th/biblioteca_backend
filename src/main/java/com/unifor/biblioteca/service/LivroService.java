package com.unifor.biblioteca.service;

import com.unifor.biblioteca.dto.LivroResponseDTO;
import com.unifor.biblioteca.model.Livro;
import com.unifor.biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public LivroService() {}

    public List<LivroResponseDTO> pegarTodos() {
        List<Livro> livrosBanco = livroRepository.findAll();
        
        return livrosBanco.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public LivroResponseDTO pegar(int id) {
        Optional<Livro> livroBanco = livroRepository.findById(id);
        return livroBanco.map(this::toDTO).orElse(null);
    }

    public LivroResponseDTO cadastrar(LivroResponseDTO livroDTO) {
        // Converte DTO -> Entidade para salvar no banco
        Livro livroParaSalvar = toEntity(livroDTO);
        
        // O banco gera o ID automaticamente
        Livro livroSalvo = livroRepository.save(livroParaSalvar);
        
        // Retorna o DTO com o ID gerado
        return toDTO(livroSalvo);
    }

    // --- Métodos Auxiliares de Conversão ---
    
    private LivroResponseDTO toDTO(Livro livro) {
        return new LivroResponseDTO(
            livro.getId(),
            livro.getTitulo(),
            livro.getAutor(),
            livro.getAno(),
            livro.getStatus()
        );
    }

    private Livro toEntity(LivroResponseDTO dto) {
        return new Livro(
            dto.getTitulo(),
            dto.getAutor(),
            dto.getAno(),
            dto.getStatus()
        );
    }
}