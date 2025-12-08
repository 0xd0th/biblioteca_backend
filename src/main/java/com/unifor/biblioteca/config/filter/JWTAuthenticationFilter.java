package com.unifor.biblioteca.config.filter;

import com.unifor.biblioteca.service.AccountUserDetailService;
import com.unifor.biblioteca.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;



import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter  {

    private JWTService jwtService;
    private AccountUserDetailService userDetailService;

    public JWTAuthenticationFilter( JWTService jwtService,
                                    AccountUserDetailService userDetailService) {
        this.jwtService = jwtService;
        this.userDetailService = userDetailService;
    }

    @Override
    public void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) {

        String authHeader = request.getHeader("Authorization");

        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            String jwt = authHeader.substring(7);
            String userMatricula = jwtService.extractMatricula(jwt);

            if (
                    userMatricula != null &&
                    SecurityContextHolder.getContext().getAuthentication() == null
            ) {

                UserDetails userDetails = userDetailService.loadUserByUsername(userMatricula);

                if (jwtService.validateToken(jwt)) {

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            filterChain.doFilter(request, response);

        } catch (Exception e) {
            System.out.println("error ao aplicar filtro: " + e.getMessage());
        }
    }





}
