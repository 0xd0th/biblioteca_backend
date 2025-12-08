package com.unifor.biblioteca.service;

import com.unifor.biblioteca.dto.LoginRequestDTO;
import com.unifor.biblioteca.dto.LoginResponseDTO;
import com.unifor.biblioteca.exception.AuthenticationException;
import com.unifor.biblioteca.model.User;
import com.unifor.biblioteca.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JWTService jwtService;

    public AuthenticationService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JWTService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponseDTO authenticate( LoginRequestDTO loginRequest ) throws AuthenticationException {

        String errorMessage = "credenciais inválidas";

        User user = userRepository.findByMatricula(loginRequest.getMatricula());
        if ( user == null )
            throw new AuthenticationException(errorMessage);

        if ( !passwordEncoder.matches(loginRequest.getSenha(), user.getSenhaCodificada()) )
            throw new AuthenticationException(errorMessage);

        String token = jwtService.generateToken(user);
        return new LoginResponseDTO(token, user.getMatricula());

    }

}
