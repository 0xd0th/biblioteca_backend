

package com.unifor.biblioteca.service;

import com.unifor.biblioteca.dto.UserRequestDTO;
import com.unifor.biblioteca.dto.UserResponseDTO;
import com.unifor.biblioteca.model.Jogo;
import com.unifor.biblioteca.model.User;
import com.unifor.biblioteca.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JWTService jwtService;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JWTService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String cadastrar( UserRequestDTO dto ) {

        User user = userRepository.findByMatricula(dto.getMatricula());
        if ( user != null )
            return "usuário já cadastrado";

        userRepository.save(toEntity(dto));

        return "usuário cadastrado com sucesso";

    }

    public UserResponseDTO pegar( String authHeader ) {
        String token = authHeader.substring(7);

        if( !jwtService.validateToken(token) )
            return null;

        String matricula = jwtService.extractMatricula(token);
        User user = userRepository.findByMatricula(matricula);

        if ( user == null )
            return null;

        return toDTO(user);

    }




    private UserResponseDTO toDTO(User user) {

        return new UserResponseDTO(
                user.getMatricula(),
                user.getNome(),
                user.getSobrenome(),
                user.getCurso()
        );

    }

    private User toEntity(UserRequestDTO dto) {

        User user = new User();
        user.setMatricula(dto.getMatricula());
        user.setNome(dto.getNome());
        user.setSobrenome(dto.getSobrenome());
        user.setCurso(dto.getCurso());
        user.setSenhaCodificada(passwordEncoder.encode(dto.getSenha()));

        return user;
    }
}