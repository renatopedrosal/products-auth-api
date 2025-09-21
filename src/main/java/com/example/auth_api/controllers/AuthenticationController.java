package com.example.auth_api.controllers;

import com.example.auth_api.domain.user.AuthenticationDTO;
import com.example.auth_api.domain.user.LoginResponseDTO;
import com.example.auth_api.domain.user.RegisterDTO;
import com.example.auth_api.domain.user.User;
import com.example.auth_api.infra.security.TokenService;
import com.example.auth_api.repositories.UserRepository;
import com.example.auth_api.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@Tag(name = "Autenticação", description = "Endpoints de login e registro")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    @Operation(summary = "Realiza login", description = "Autenticação de usuário, retorna token JWT")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        LoginResponseDTO response = authenticationService.login(data);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    @Operation(summary = "Registra usuário", description = "Cria uma nova conta de usuário")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO data) {
        if (authenticationService.register(data)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
