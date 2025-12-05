package com.unifor.biblioteca.controller;

import com.unifor.biblioteca.dto.JogoDTO;
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
    public ResponseEntity<List<JogoDTO>> listar() {
        return ResponseEntity.ok(jogoService.pegarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JogoDTO> pegar(@PathVariable int id) {
        JogoDTO jogo = jogoService.pegarPorId(id);
        if (jogo != null) return ResponseEntity.ok(jogo);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody JogoDTO jogo) {
        jogoService.cadastrar(jogo);
        return ResponseEntity.ok("jogo cadastrado");
    }
}