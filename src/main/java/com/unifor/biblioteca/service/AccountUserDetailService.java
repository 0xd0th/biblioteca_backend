package com.unifor.biblioteca.service;


import com.unifor.biblioteca.data.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static io.jsonwebtoken.lang.Collections.setOf;

@Service
public class AccountUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    public AccountUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername( String matricula ) {
        com.unifor.biblioteca.data.model.User user = userRepository.findByMatricula(matricula);

        if( user == null )
            throw new UsernameNotFoundException("Usuário não encontrado: $username");

        return new User(
            user.getMatricula(),
                user.getSenhaCodificada(),
                setOf(new SimpleGrantedAuthority("USER"))
        );


    }




}

