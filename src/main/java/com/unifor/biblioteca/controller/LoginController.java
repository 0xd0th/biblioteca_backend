package com.unifor.biblioteca.controller;

import com.unifor.biblioteca.dto.LoginRequestDTO;
import com.unifor.biblioteca.dto.LoginResponseDTO;
import com.unifor.biblioteca.exception.AuthenticationException;
import com.unifor.biblioteca.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private AuthenticationService authenticationService;

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> autenticar(@RequestBody LoginRequestDTO loginRequest ) {
        LoginResponseDTO loginResponse;

        try {
            loginResponse = authenticationService.authenticate(loginRequest);
        } catch (AuthenticationException e ) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(loginResponse);
    }


}
