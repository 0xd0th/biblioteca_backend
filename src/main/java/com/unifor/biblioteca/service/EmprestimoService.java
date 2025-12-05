package com.unifor.biblioteca.service;

import com.unifor.biblioteca.dto.EmprestimoRequestDTO;
import com.unifor.biblioteca.model.*;
import com.unifor.biblioteca.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmprestimoService {

    @Autowired
    private UserRepository userRepository;

    // --- Parte de Livros ---
    @Autowired
    private EmprestimoLivroRepository emprestimoLivroRepository;
    @Autowired
    private LivroRepository livroRepository;

    // --- Parte de Jogos ---
    @Autowired
    private EmprestimoJogoRepository emprestimoJogoRepository;
    @Autowired
    private JogoRepository jogoRepository;


    // Lógica para LIVROS
    public String realizarEmprestimoLivro(EmprestimoRequestDTO dados) {
        User aluno = userRepository.findByMatricula(dados.getMatricula());
        if (aluno == null) return "Erro: Aluno não encontrado!";

        Livro livro = livroRepository.findById(dados.getIdItem()).orElse(null);
        if (livro == null) return "Erro: Livro não encontrado!";

        if (!"DISPONIVEL".equalsIgnoreCase(livro.getStatus())) {
            return "Erro: Este livro já está emprestado ou indisponível!";
        }

        EmprestimoLivro emprestimo = new EmprestimoLivro(aluno, livro, LocalDate.now(), LocalDate.now().plusDays(7));
        emprestimoLivroRepository.save(emprestimo);

        livro.setStatus("INDISPONIVEL");
        livroRepository.save(livro);

        return "Sucesso: Livro '" + livro.getTitulo() + "' emprestado para " + aluno.getNome();
    }

    // Lógica para JOGOS (Nova)
    public String realizarEmprestimoJogo(EmprestimoRequestDTO dados) {
        User aluno = userRepository.findByMatricula(dados.getMatricula());
        if (aluno == null) return "Erro: Aluno não encontrado!";

        Jogo jogo = jogoRepository.findById(dados.getIdItem()).orElse(null);
        if (jogo == null) return "Erro: Jogo não encontrado!";

        if (!"DISPONIVEL".equalsIgnoreCase(jogo.getStatus())) {
            return "Erro: Este jogo já está emprestado ou em manutenção!";
        }

        // Criamos o empréstimo (Regra: Jogos devolvem em 3 dias, por exemplo)
        EmprestimoJogo emprestimo = new EmprestimoJogo(aluno, jogo, LocalDate.now(), LocalDate.now().plusDays(3));
        emprestimoJogoRepository.save(emprestimo);

        jogo.setStatus("INDISPONIVEL");
        jogoRepository.save(jogo);

        return "Sucesso: Jogo '" + jogo.getTitulo() + "' emprestado para " + aluno.getNome();
    }

    // --- DEVOLUÇÃO DE LIVRO ---
    public String devolverLivro(Integer idLivro) {
        // 1. Achar o livro
        Livro livro = livroRepository.findById(idLivro).orElse(null);
        if (livro == null) return "Erro: Livro não encontrado.";

        // 2. Achar o empréstimo ativo desse livro
        EmprestimoLivro emprestimo = emprestimoLivroRepository.findByLivroAndStatus(livro, "ATIVO")
                .orElse(null);

        if (emprestimo == null) {
            return "Erro: Não há empréstimo ativo para este livro. Ele já está na estante?";
        }

        // 3. Finalizar o empréstimo
        emprestimo.setDataDevolucaoReal(LocalDate.now());
        emprestimo.setStatus("FINALIZADO");
        emprestimoLivroRepository.save(emprestimo);

        // 4. Liberar o livro
        livro.setStatus("DISPONIVEL");
        livroRepository.save(livro);

        return "Sucesso: O livro '" + livro.getTitulo() + "' foi devolvido!";
    }

    // --- DEVOLUÇÃO DE JOGO ---
    public String devolverJogo(Integer idJogo) {
        Jogo jogo = jogoRepository.findById(idJogo).orElse(null);
        if (jogo == null) return "Erro: Jogo não encontrado.";

        EmprestimoJogo emprestimo = emprestimoJogoRepository.findByJogoAndStatus(jogo, "ATIVO")
                .orElse(null);

        if (emprestimo == null) {
            return "Erro: Não há empréstimo ativo para este jogo.";
        }

        emprestimo.setStatus("FINALIZADO");
        emprestimoJogoRepository.save(emprestimo);

        jogo.setStatus("DISPONIVEL");
        jogoRepository.save(jogo);

        emprestimo.setDataDevolucaoReal(LocalDate.now());

        return "Sucesso: O jogo '" + jogo.getTitulo() + "' foi devolvido!";
    }
}