package com.unifor.biblioteca.controller;

import com.unifor.biblioteca.dto.JogoResponseDTO;
import com.unifor.biblioteca.service.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogo")
public class JogoController {

    @Autowired
    private JogoService jogoService;

    public JogoController(JogoService jogoService) {
        this.jogoService = jogoService;
    }

    @GetMapping
    public ResponseEntity<List<JogoResponseDTO>> listar() {
        return ResponseEntity.ok(jogoService.pegarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JogoResponseDTO> pegar(@PathVariable int id) {
        JogoResponseDTO jogo = jogoService.pegarPorId(id);
        if (jogo != null) return ResponseEntity.ok(jogo);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody JogoResponseDTO jogo) {
        jogoService.cadastrar(jogo);
        return ResponseEntity.ok("jogo cadastrado");
    }
}