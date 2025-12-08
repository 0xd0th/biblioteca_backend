package com.unifor.biblioteca.controller;

import com.unifor.biblioteca.controller.dto.LivroResponseDTO;
import com.unifor.biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired // Adicionei o Autowired para o Spring gerenciar
    private LivroService livroService;

    // Mantive o construtor caso o Spring prefira usar ele
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    public ResponseEntity<List<LivroResponseDTO>> listar() {
        return ResponseEntity.ok(livroService.pegarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> pegar(@PathVariable int id) {
        LivroResponseDTO livro = livroService.pegar(id);
        if (livro != null) {
            return ResponseEntity.ok(livro);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody LivroResponseDTO livro) {
        LivroResponseDTO livroCadastrado = livroService.cadastrar(livro);
        if (livroCadastrado == null)
            return ResponseEntity.badRequest().body("erro ao cadastrar livro");

        return ResponseEntity.ok("Livro cadastrado com ID: " + livroCadastrado.getId());
    }
}