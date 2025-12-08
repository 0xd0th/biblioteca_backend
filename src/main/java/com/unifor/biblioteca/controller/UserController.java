
package com.unifor.biblioteca.controller;

import com.unifor.biblioteca.dto.UserRequestDTO;
import com.unifor.biblioteca.dto.LoginRequestDTO;
import com.unifor.biblioteca.dto.UserResponseDTO;
import com.unifor.biblioteca.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController( UserService userService ) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody UserRequestDTO usuario) {
        String responseMessage = userService.cadastrar(usuario);
        return ResponseEntity.ok(responseMessage);
    }

    @GetMapping
    public ResponseEntity<UserResponseDTO> pegarUsuario(HttpServletRequest request) {
        return ResponseEntity.ok(userService.pegar(request.getHeader("Authorization")));
    }


}
