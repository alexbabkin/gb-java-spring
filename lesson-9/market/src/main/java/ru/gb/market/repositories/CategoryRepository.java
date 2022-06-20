package ru.gb.market.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.market.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByTitle(String title);
}
