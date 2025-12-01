
package com.unifor.biblioteca.controller;

import com.unifor.biblioteca.dto.UserRequestDTO;
import com.unifor.biblioteca.dto.LoginRequestDTO;
import com.unifor.biblioteca.dto.UserResponseDTO;
import com.unifor.biblioteca.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController( UserService userService ) {
        this.userService = userService;
    }

    @PostMapping("/logar")
    public ResponseEntity<UserResponseDTO> logar(@RequestBody LoginRequestDTO usuario) {
        return null;
    };

    @PostMapping("/cadastrar")
    public void cadastrar(@RequestBody UserRequestDTO usuario) {
        userService.cadastrar(usuario);
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<UserResponseDTO> pegarUsuario( @PathVariable int matricula ) {
        return ResponseEntity.ok(userService.pegar(matricula));
    }


}
