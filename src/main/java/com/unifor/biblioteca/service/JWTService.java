package com.unifor.biblioteca.service;

import com.unifor.biblioteca.data.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.secret}") private String secret;
    @Value("${jwt.expiration-hours}")private Long expirationHours;

    private SecretKey secretKey;

    private SecretKey getSecretKey() {

        if ( this.secretKey == null )
            secretKey = Keys.hmacShaKeyFor(secret.getBytes());

        return secretKey;

    }

    public String generateToken(User user) {
        Instant now = Instant.now();
        Duration expirationDuration = Duration.ofHours(expirationHours);
        Instant expirationInstant = now.plus(expirationDuration);

        return Jwts.builder()
                .subject(user.getMatricula())
                .issuedAt(Date.from(now))
                .expiration(Date.from(expirationInstant))
                .signWith(getSecretKey())
                .compact();
    }

    public Boolean validateToken( String token ) {
        try  {
            Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token);
        } catch ( Exception e) {
            return false;
        }

        return true;

    }

    public String extractMatricula( String token ) {

        Claims claims;

        try {
            claims = Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch ( Exception e ){
            return null;
        }

        return claims.getSubject();


    }

    public String extractToken(HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");
        return authHeader.substring(7);

    }


}
