package com.unifor.biblioteca.controller;

import com.unifor.biblioteca.controller.dto.EmprestimoRequestDTO;
import com.unifor.biblioteca.controller.dto.LivroResponseDTO;
import com.unifor.biblioteca.service.EmprestimoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @GetMapping("/livro")
    public ResponseEntity<LivroResponseDTO> pegarLivrosEmprestados( HttpServletRequest request ) {
        emprestimoService
    }

    @GetMapping("/livro/{idLivro}")
    public ResponseEntity<String> emprestarLivro(@PathVariable Integer idLivro, HttpServletRequest request) {
        String resultado = emprestimoService.realizarEmprestimoLivro(request, idLivro);
        if (resultado.startsWith("Erro")) return ResponseEntity.badRequest().body(resultado);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/jogo/{idJogo}")
    public ResponseEntity<String> emprestarJogo(@PathVariable Integer idJogo, HttpServletRequest request) {
        String resultado = emprestimoService.realizarEmprestimoJogo(request, idJogo);
        if (resultado.startsWith("Erro")) return ResponseEntity.badRequest().body(resultado);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/devolucao/livro/{idLivro}")
    public ResponseEntity<String> devolverLivro(@PathVariable Integer idLivro, HttpServletRequest request) {
        String resultado = emprestimoService.devolverLivro(request, idLivro);
        if (resultado.startsWith("Erro")) return ResponseEntity.badRequest().body(resultado);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/devolucao/jogo/{idJogo}")
    public ResponseEntity<String> devolverJogo(@PathVariable Integer idJogo, HttpServletRequest request) {
        String resultado = emprestimoService.devolverJogo(request, idJogo);
        if (resultado.startsWith("Erro")) return ResponseEntity.badRequest().body(resultado);
        return ResponseEntity.ok(resultado);
    }
}