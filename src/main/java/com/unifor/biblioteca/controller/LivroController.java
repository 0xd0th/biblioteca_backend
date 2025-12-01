
package com.unifor.biblioteca.controller;

import com.unifor.biblioteca.dto.LivroDTO;
import com.unifor.biblioteca.service.LivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {

    private LivroService livroService;

    public LivroController( LivroService livroService ) {
        this.livroService = livroService;
    }

    @GetMapping
    public ResponseEntity<List<LivroDTO>> listar() {
        return ResponseEntity.ok(livroService.pegarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> pegar(@PathVariable int id) {
        return ResponseEntity.ok(livroService.pegar(id));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar( @RequestBody LivroDTO livro) {
        LivroDTO livroCadastrado = livroService.cadastrar(livro);
        if ( livroCadastrado == null )
            return ResponseEntity.badRequest().body("erro ao cadastrar livro");

        return ResponseEntity.ok("livro cadastrado");
    }


}
