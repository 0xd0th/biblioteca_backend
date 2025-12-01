
package com.unifor.biblioteca.service;

import com.unifor.biblioteca.dto.JogoDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JogoService {

    private Map<Integer, JogoDTO> jogos = new HashMap<>();

    public JogoService() {
        jogos.put(1,  new JogoDTO(1,  "Xadrez", "DISPONIVEL"));
        jogos.put(2,  new JogoDTO(2,  "Damas", "DISPONIVEL"));
        jogos.put(3,  new JogoDTO(3,  "Banco Imobiliário", "INDISPONIVEL"));
        jogos.put(4,  new JogoDTO(4,  "WAR", "DISPONIVEL"));
        jogos.put(5,  new JogoDTO(5,  "Detetive", "DISPONIVEL"));
        jogos.put(6,  new JogoDTO(6,  "Jogo da Vida", "INDISPONIVEL"));
        jogos.put(7,  new JogoDTO(7,  "UNO", "DISPONIVEL"));
        jogos.put(8,  new JogoDTO(8,  "Imagem & Ação", "DISPONIVEL"));
        jogos.put(9,  new JogoDTO(9,  "Jenga", "INDISPONIVEL"));
        jogos.put(10, new JogoDTO(10, "Perfil", "DISPONIVEL"));
    }

    public List<JogoDTO> pegarTodos() {
        return this.jogos.values().stream().toList();
    }

    public JogoDTO pegarPorId( int id ) {
        return this.jogos.get(id);
    }

    public void cadastrar( JogoDTO jogo ) {
        jogos.put(jogo.getId(), jogo);
    }


}