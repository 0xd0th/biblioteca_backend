
package com.unifor.biblioteca.service;

import com.unifor.biblioteca.dto.LivroDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LivroService {

    private Map<Integer, LivroDTO> livros = new HashMap<>();

    public LivroService () {

        livros.put(1, new LivroDTO(1, "Introdução à Programação", "Ana Silva", 2018, "DISPONIVEL"));
        livros.put(2, new LivroDTO(2, "Algoritmos e Estruturas de Dados", "Bruno Oliveira", 2020, "INDISPONIVEL"));
        livros.put(3, new LivroDTO(3, "Banco de Dados Relacionais", "Carla Souza", 2019, "DISPONIVEL"));
        livros.put(4, new LivroDTO(4, "Redes de Computadores", "Diego Pereira", 2017, "INDISPONIVEL"));
        livros.put(5, new LivroDTO(5, "Sistemas Operacionais", "Eduarda Almeida", 2016, "DISPONIVEL"));
        livros.put(6, new LivroDTO(6, "Engenharia de Software", "Felipe Costa", 2021, "INDISPONIVEL"));
        livros.put(7, new LivroDTO(7, "Padrões de Projeto", "Gabriela Rodrigues", 2015, "DISPONIVEL"));
        livros.put(8, new LivroDTO(8, "Desenvolvimento Web", "Henrique Barbosa", 2022, "DISPONIVEL"));
        livros.put(9, new LivroDTO(9, "Arquitetura de Computadores", "Isabela Gomes", 2014, "INDISPONIVEL"));
        livros.put(10, new LivroDTO(10, "Inteligência Artificial", "João Ferreira", 2023, "DISPONIVEL"));

    }

    public List<LivroDTO> pegarTodos() {
        return this.livros.values().stream().toList();
    }

    public LivroDTO pegar( int id ) {
        return this.livros.get(id);
    }

    public LivroDTO cadastrar( LivroDTO livro ) {
        this.livros.put(livro.getId(), livro);

        return this.livros.get(livro.getId());
    }


}