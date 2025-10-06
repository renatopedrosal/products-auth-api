package com.example.auth_api.services;

import com.example.auth_api.domain.product.Product;
import com.example.auth_api.domain.product.ProductRequestDTO;
import com.example.auth_api.exceptions.ProductNotFoundException;
import com.example.auth_api.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product findProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException());
    }

    public Product createProduct(ProductRequestDTO requestDTO) {
        Product newProduct = new Product(requestDTO);
        return productRepository.save(newProduct);
    }

    public Product updateProduct(String id, Product product) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setPrice(product.getPrice());
                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new ProductNotFoundException());
    }

    public void deleteProduct(String id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException();
        }

        productRepository.deleteById(id);
    }
}
