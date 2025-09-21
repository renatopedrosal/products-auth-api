package com.example.auth_api.domain.user;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "RegisterDTO", description = "Dados para registro de usuário")
public record RegisterDTO(
        @Schema(description = "Login do usuário", example = "admin@email.com")
        String login,
        @Schema(description = "Senha do usuário", example = "123456")
        String password,
        @Schema(description = "Permissão para acesso", example = "ADMIN")
        UserRole role
) {}
