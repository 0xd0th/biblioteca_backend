
package com.unifor.biblioteca.controller;

import com.unifor.biblioteca.dto.JogoDTO;
import com.unifor.biblioteca.service.JogoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogo")
public class JogoController {

    private JogoService jogoService;

    public JogoController( JogoService jogoService ) {
        this.jogoService = jogoService;
    }

    @GetMapping
    public ResponseEntity<List<JogoDTO>> listar() {
        return ResponseEntity.ok(jogoService.pegarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JogoDTO> pegar( @PathVariable int id ) {
        return ResponseEntity.ok(jogoService.pegarPorId(id));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar( @RequestBody JogoDTO jogo ) {
        jogoService.cadastrar(jogo);
        return ResponseEntity.ok("jogo cadastrado");
    }
}