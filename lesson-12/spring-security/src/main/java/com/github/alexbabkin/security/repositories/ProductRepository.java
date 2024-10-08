package com.github.alexbabkin.security.repositories;

import com.github.alexbabkin.security.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    void deleteById(Long id);
}
