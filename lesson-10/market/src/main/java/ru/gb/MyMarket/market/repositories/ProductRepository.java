package ru.gb.MyMarket.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.MyMarket.market.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    void deleteById(Long id);
}
