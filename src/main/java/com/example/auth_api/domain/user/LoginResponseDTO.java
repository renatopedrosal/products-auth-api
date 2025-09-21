package com.example.auth_api.domain.user;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "LoginResponseDTO", description = "Resposta do login contendo o token JWT")
public record LoginResponseDTO(
        @Schema(description = "Token JWT de autenticação", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6...")
        String token
) { }
