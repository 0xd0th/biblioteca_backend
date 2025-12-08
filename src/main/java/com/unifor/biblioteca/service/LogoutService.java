package com.unifor.biblioteca.service;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.juli.logging.Log;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LogoutService {

    private JWTService jwtService;

    public LogoutService( JWTService jwtService ) {
        this.jwtService = jwtService;
    }

    private Map<String, List<String>> blackList = new HashMap<>();

    public void adicionar(HttpServletRequest request) {

        String token = jwtService.extractToken(request);
        String matricula = jwtService.extractMatricula(token);

        blackList.computeIfAbsent(matricula, k -> new ArrayList<>()).add(token);
    }

    public Boolean consultar(String matricula, String token) {

        List<String> tokens = blackList.get(matricula);

        if (tokens == null) {
            return false;
        }

        return tokens.contains(token);
    }


}
