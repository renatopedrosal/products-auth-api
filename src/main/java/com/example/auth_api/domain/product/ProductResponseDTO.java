package com.example.auth_api.domain.product;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ProductResponseDTO", description = "Resposta da busca por produtos")
public record ProductResponseDTO(
        @Schema(description = "ID do produto", example = "1")
        String id,

        @Schema(description = "Nome do produto", example = "Teclado")
        String name,

        @Schema(description = "Preço do produto", example = "10.00")
        Integer price
) {
    public ProductResponseDTO(Product product){
        this(product.getId(), product.getName(), product.getPrice());
    }
}
