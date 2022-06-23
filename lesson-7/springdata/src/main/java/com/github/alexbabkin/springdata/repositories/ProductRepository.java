package com.github.alexbabkin.springdata.repositories;

import com.github.alexbabkin.springdata.models.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :min AND :max")
    List<Product> findProductBetweenPrices(Integer min, Integer max);

    List<Product> findProductsByPriceLessThanEqual(Integer maxPrice);

    List<Product> findProductsByPriceGreaterThanEqual(Integer minPrice);
}
