package com.example.auth_api.controllers;

import com.example.auth_api.domain.product.Product;
import com.example.auth_api.domain.product.ProductRequestDTO;
import com.example.auth_api.domain.product.ProductResponseDTO;
import com.example.auth_api.services.ProductService;
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
    ProductService productService;

    @GetMapping
    @Operation(summary = "Listar produtos", description = "Retorna todos os produtos")
    @ApiResponse(responseCode = "200", description = "Lista de produtos",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Product.class))))
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> productList = this.productService.findAllProducts()
                .stream().map(ProductResponseDTO::new).toList();

        return ResponseEntity.ok(productList);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar produto por ID")
    @ApiResponse(responseCode = "200", description = "Produto encontrado",
            content = @Content(schema = @Schema(implementation = Product.class)))
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    public ResponseEntity<Product> getById(@PathVariable String id) {
        return ResponseEntity.ok(productService.findProductById(id));
    }

    @PostMapping
    @Operation(summary = "Criar novo produto")
    @ApiResponse(responseCode = "201", description = "Produto criado",
            content = @Content(schema = @Schema(implementation = Product.class)))
    public ResponseEntity<Product> postProduct(@RequestBody @Valid ProductRequestDTO body) {
        Product newProduct = productService.createProduct(body);
        return ResponseEntity.ok(newProduct);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar produto existente")
    @ApiResponse(responseCode = "200", description = "Produto atualizado",
            content = @Content(schema = @Schema(implementation = Product.class)))
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    public ResponseEntity<Product> update(@PathVariable @Valid String id, @RequestBody @Valid Product product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar produto existente")
    @ApiResponse(responseCode = "204", description = "Produto deletado")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    public ResponseEntity<Void> delete(@PathVariable @Valid String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
