package ru.gb.MyMarket.market.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.MyMarket.market.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByTitle(String title);
}
