

package com.unifor.biblioteca.service;

import com.unifor.biblioteca.dto.UserRequestDTO;
import com.unifor.biblioteca.dto.UserResponseDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private Map<Integer, UserResponseDTO> usuarios = new HashMap<>();


    public UserService() {

        // banco de dados temporario
        usuarios.put(1, new UserResponseDTO(1, "Ana", "Silva", "Engenharia de Software"));
        usuarios.put(2, new UserResponseDTO(2, "Bruno", "Oliveira", "Ciência da Computação"));
        usuarios.put(3, new UserResponseDTO(3, "Carla", "Souza", "Sistemas de Informação"));
        usuarios.put(4, new UserResponseDTO(4, "Diego", "Pereira", "Engenharia Elétrica"));
        usuarios.put(5, new UserResponseDTO(5, "Eduarda", "Almeida", "Matemática"));
        usuarios.put(6, new UserResponseDTO(6, "Felipe", "Costa", "Física"));
        usuarios.put(7, new UserResponseDTO(7, "Gabriela", "Rodrigues", "Engenharia Civil"));
        usuarios.put(8, new UserResponseDTO(8, "Henrique", "Barbosa", "Arquitetura"));
        usuarios.put(9, new UserResponseDTO(9, "Isabela", "Gomes", "Design"));
        usuarios.put(10, new UserResponseDTO(10, "João", "Ferreira", "Análise e Desenvolvimento de Sistemas"));
        //

    }

    public UserResponseDTO cadastrar( UserRequestDTO usuario ) {
        this.usuarios.put(usuario.getMatricula(), new UserResponseDTO(
                usuario.getMatricula(),
                usuario.getNome(),
                usuario.getSobrenome(),
                usuario.getCurso()
        ));

        return this.usuarios.get(usuario.getMatricula());
    }

    public UserResponseDTO pegar( int matricula ) {
        return this.usuarios.get(matricula);
    }
}