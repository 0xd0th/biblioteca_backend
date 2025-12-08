
package com.unifor.biblioteca.controller;

import com.unifor.biblioteca.controller.dto.UserRequestDTO;
import com.unifor.biblioteca.controller.dto.UserResponseDTO;
import com.unifor.biblioteca.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController( UserService userService ) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> cadastrar(@RequestBody UserRequestDTO usuario) {
        String responseMessage = userService.cadastrar(usuario);
        return ResponseEntity.ok(Map.of("message", "usuário cadastrado com sucesso"));
    }

    @GetMapping
    public ResponseEntity<UserResponseDTO> pegarUsuario(HttpServletRequest request) {
        return ResponseEntity.ok(userService.pegar(request));
    }


}
