package com.github.alexbabkin.product;

import java.util.ArrayList;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class Repository {
    private final ArrayList<Product> products = new ArrayList<>();

    public Repository() {
        for (int i = 1; i <= 5; i++) {
            products.add(new Product(i, String.format("Product %d", i), i * 50.5));
        }
    }

    public ArrayList<Product> getAll() {
        return products;
    }

    public Product getById(Integer id) {
        if (id == null) return null;

        return products.stream().filter(product -> product.getId() == id).findFirst().orElse(null);
    }
}
