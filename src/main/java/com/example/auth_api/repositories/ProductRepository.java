package com.example.auth_api.repositories;

import com.example.auth_api.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
