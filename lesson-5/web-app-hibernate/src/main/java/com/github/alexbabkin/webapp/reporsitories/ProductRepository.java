package com.github.alexbabkin.webapp.reporsitories;

import com.github.alexbabkin.webapp.models.Product;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class ProductRepository {
    private final List<Product> products = new ArrayList<>();

    @PostConstruct
    public void init() {
        products.addAll(
                Arrays.asList(
                        new Product(1L, "Product 1", 100.5),
                        new Product(2L, "Product 2", 200.5),
                        new Product(3L, "Product 3", 300.5),
                        new Product(4L, "Product 4", 400.5)));
    }

    public Collection<Product> getAll() {
        return Collections.unmodifiableCollection(products);
    }

    public void save(Product product) {
        products.add(product);
    }

    public Product findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }
}
