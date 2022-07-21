package com.github.alexbabkin.market.repositories;

import com.github.alexbabkin.market.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    void deleteById(Long id);
}
