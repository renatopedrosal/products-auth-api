package com.example.auth_api.services;

import com.example.auth_api.domain.user.AuthenticationDTO;
import com.example.auth_api.domain.user.LoginResponseDTO;
import com.example.auth_api.domain.user.RegisterDTO;
import com.example.auth_api.domain.user.User;
import com.example.auth_api.infra.security.TokenService;
import com.example.auth_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;

    public LoginResponseDTO login(AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return new LoginResponseDTO(token);
    }

    public boolean register(RegisterDTO data) {
        // Check if user already exists
        if (this.repository.findByLogin(data.login()) != null) {
            return false;
        }

        // Encrypt password and create a new user
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        // Save the new user
        this.repository.save(newUser);
        return true;
    }
}

