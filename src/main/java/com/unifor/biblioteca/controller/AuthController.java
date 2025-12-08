package com.unifor.biblioteca.controller;

import com.unifor.biblioteca.controller.dto.LoginRequestDTO;
import com.unifor.biblioteca.controller.dto.LoginResponseDTO;
import com.unifor.biblioteca.exception.AuthenticationException;
import com.unifor.biblioteca.service.AuthenticationService;
import com.unifor.biblioteca.service.LogoutService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private AuthenticationService authenticationService;
    private LogoutService logoutService;

    public AuthController(
            AuthenticationService authenticationService,
            LogoutService logoutService
    ) {
        this.authenticationService = authenticationService;
        this.logoutService = logoutService;
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

    @GetMapping("/logout")
    public ResponseEntity<Map<String,String>> deslogar(HttpServletRequest request) {
        logoutService.adicionar(request);
        return ResponseEntity.ok(Map.of("message", "usuario deslogado"));
    }



}
