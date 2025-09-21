package com.example.auth_api.domain.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(name = "ProductRequestDTO", description = "Requisição de Produto")
public record ProductRequestDTO(
        @NotBlank
        @Schema(description = "Nome do produto", example = "Teclado")
        String name,

        @NotNull
        @Schema(description = "Preço do produto", example = "10.00")
        Integer price
) {
}
