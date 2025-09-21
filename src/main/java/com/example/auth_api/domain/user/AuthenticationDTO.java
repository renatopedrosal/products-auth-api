package com.example.auth_api.domain.user;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "AuthenticationDTO", description = "Credenciais de login")
public record AuthenticationDTO(
        @Schema(description = "Login do usuário", example = "user@email.com")
        String login,
        @Schema(description = "Senha do usuário", example = "123456")
        String password
) {}
