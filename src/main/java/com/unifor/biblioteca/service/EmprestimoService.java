package com.unifor.biblioteca.service;

import com.unifor.biblioteca.controller.dto.EmprestimoRequestDTO;
import com.unifor.biblioteca.controller.dto.JogoResponseDTO;
import com.unifor.biblioteca.controller.dto.LivroResponseDTO;
import com.unifor.biblioteca.data.model.*;
import com.unifor.biblioteca.data.repository.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmprestimoService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTService jwtService;

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

    public List<LivroResponseDTO> pegarTodosLivros( HttpServletRequest request ) {
        String token = jwtService.extractToken(request);
        String matricula = jwtService.extractMatricula(token);

        User aluno = userRepository.findByMatricula(matricula);
        List<EmprestimoLivro> emprestimos = emprestimoLivroRepository.findByUserId(aluno.getId());

        List<LivroResponseDTO> livrosdtos = new ArrayList<>();
        for( EmprestimoLivro emprestimo : emprestimos ) {
            Livro livro = emprestimo.getLivro();
            livrosdtos.add(new LivroResponseDTO(
                    livro.getId(),
                    livro.getTitulo(),
                    livro.getAutor(),
                    livro.getAno(),
                    livro.getStatus()
            ));
        }

        return livrosdtos;

    }

    public List<JogoResponseDTO> pegarTodosJogos( HttpServletRequest request ) {
        String token = jwtService.extractToken(request);
        String matricula = jwtService.extractMatricula(token);

        User aluno = userRepository.findByMatricula(matricula);
        List<EmprestimoJogo> emprestimos = emprestimoJogoRepository.findByUserId(aluno.getId());

        List<JogoResponseDTO> jogosdtos = new ArrayList<>();
        for ( EmprestimoJogo emprestimo : emprestimos ) {
            Jogo jogo = emprestimo.getJogo();
            jogosdtos.add( new JogoResponseDTO(
                    jogo.getId(),
                    jogo.getTitulo(),
                    jogo.getMaxJogadores(),
                    jogo.getMinJogadores(),
                    jogo.getEditora(),
                    jogo.getGenero(),
                    jogo.getStatus()
            ));
        }

        return jogosdtos;

    }



        // Lógica para LIVROS
    public String realizarEmprestimoLivro(HttpServletRequest request, int idLivro) {

        String token = jwtService.extractToken(request);
        String matricula = jwtService.extractMatricula(token);

        User aluno = userRepository.findByMatricula(matricula);
        if (aluno == null) return "Erro: Aluno não encontrado!";

        Livro livro = livroRepository.findById(idLivro).orElse(null);
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
    public String realizarEmprestimoJogo(HttpServletRequest request, int idJogo) {

        String token = jwtService.extractToken(request);
        String matrcula = jwtService.extractMatricula(token);

        User aluno = userRepository.findByMatricula(matrcula);
        if (aluno == null) return "Erro: Aluno não encontrado!";

        Jogo jogo = jogoRepository.findById(idJogo).orElse(null);
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
    public String devolverLivro(HttpServletRequest request, int idLivro) {

        String token = jwtService.extractToken(request);
        String matricula = jwtService.extractMatricula(token);

        // 1. Achar o livro
        Livro livro = livroRepository.findById(idLivro).orElse(null);
        if (livro == null) return "Erro: Livro não encontrado.";

        // 2. Achar o empréstimo ativo desse livro
        EmprestimoLivro emprestimo = emprestimoLivroRepository.findByLivroAndStatus(livro, "ATIVO")
                .orElse(null);

        if (emprestimo == null) {
            return "Erro: Não há empréstimo ativo para este livro. Ele já está na estante?";
        }

        if ( !(matricula.compareTo(emprestimo.getUser().getMatricula()) == 0) ) {
            return "Erro: Permissão Negada";
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
    public String devolverJogo(HttpServletRequest request, int idJogo) {

        String token = jwtService.extractToken(request);
        String matricula = jwtService.extractMatricula(token);

        Jogo jogo = jogoRepository.findById(idJogo).orElse(null);
        if (jogo == null) return "Erro: Jogo não encontrado.";

        EmprestimoJogo emprestimo = emprestimoJogoRepository.findByJogoAndStatus(jogo, "ATIVO")
                .orElse(null);

        if (emprestimo == null) {
            return "Erro: Não há empréstimo ativo para este jogo.";
        }

        if ( !(matricula.compareTo(emprestimo.getUser().getMatricula()) == 0) ) {
            return "Erro: Permissão Negada";
        }

        emprestimo.setStatus("FINALIZADO");

        emprestimo.setDataDevolucaoReal(LocalDate.now());

        emprestimoJogoRepository.save(emprestimo);

        jogo.setStatus("DISPONIVEL");
        jogoRepository.save(jogo);

        return "Sucesso: O jogo '" + jogo.getTitulo() + "' foi devolvido!";
    }


}