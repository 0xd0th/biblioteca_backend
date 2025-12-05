package com.unifor.biblioteca.controller;

import com.unifor.biblioteca.dto.EmprestimoRequestDTO;
import com.unifor.biblioteca.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping("/livro")
    public ResponseEntity<String> emprestarLivro(@RequestBody EmprestimoRequestDTO dto) {
        String resultado = emprestimoService.realizarEmprestimoLivro(dto);
        if (resultado.startsWith("Erro")) return ResponseEntity.badRequest().body(resultado);
        return ResponseEntity.ok(resultado);
    }

    @PostMapping("/jogo")
    public ResponseEntity<String> emprestarJogo(@RequestBody EmprestimoRequestDTO dto) {
        String resultado = emprestimoService.realizarEmprestimoJogo(dto);
        if (resultado.startsWith("Erro")) return ResponseEntity.badRequest().body(resultado);
        return ResponseEntity.ok(resultado);
    }

    @PostMapping("/devolucao/livro/{idLivro}")
    public ResponseEntity<String> devolverLivro(@PathVariable Integer idLivro) {
        String resultado = emprestimoService.devolverLivro(idLivro);
        if (resultado.startsWith("Erro")) return ResponseEntity.badRequest().body(resultado);
        return ResponseEntity.ok(resultado);
    }

    @PostMapping("/devolucao/jogo/{idJogo}")
    public ResponseEntity<String> devolverJogo(@PathVariable Integer idJogo) {
        String resultado = emprestimoService.devolverJogo(idJogo);
        if (resultado.startsWith("Erro")) return ResponseEntity.badRequest().body(resultado);
        return ResponseEntity.ok(resultado);
    }
}