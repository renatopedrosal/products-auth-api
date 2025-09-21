package com.example.auth_api.controllers;

import com.example.auth_api.domain.product.Product;
import com.example.auth_api.domain.product.ProductRequestDTO;
import com.example.auth_api.domain.product.ProductResponseDTO;
import com.example.auth_api.repositories.ProductRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("product")
@Tag(name = "Produtos", description = "Operações relacionadas a produtos")
@SecurityRequirement(name = "bearerAuth")
public class ProductController {
    @Autowired
    ProductRepository repository;

    @PostMapping
    @Operation(summary = "Criar novo produto")
    @ApiResponse(responseCode = "201", description = "Produto criado",
            content = @Content(schema = @Schema(implementation = Product.class)))
    public ResponseEntity postProduct(@RequestBody @Valid ProductRequestDTO body){
        Product newProduct = new Product(body);

        this.repository.save(newProduct);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(summary = "Listar produtos", description = "Retorna todos os produtos")
    @ApiResponse(responseCode = "200", description = "Lista de produtos",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Product.class))))
    public ResponseEntity getAllProducts(){
        List<ProductResponseDTO> productList = this.repository.findAll().stream().map(ProductResponseDTO::new).toList();

        return ResponseEntity.ok(productList);
    }
}
