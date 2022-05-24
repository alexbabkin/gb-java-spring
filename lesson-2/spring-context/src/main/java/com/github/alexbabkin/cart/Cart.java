package com.github.alexbabkin.cart;

import com.github.alexbabkin.product.Product;
import com.github.alexbabkin.product.Repository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Cart {
    private final ArrayList<Product> content = new ArrayList<>();

    @Autowired private Repository repository;

    public void add(Product product) {
        content.add(product);
    }

    public void add(Integer id) {
        Product product =
                repository.getAll().stream().filter(p -> p.getId() == id).findAny().orElse(null);
        if (product != null) {
            add(product);
        }
    }

    public void remove(Product product) {
        content.removeIf(p -> p.getId() == product.getId());
    }

    public void remove(Integer id) {
        content.removeIf(p -> p.getId() == id);
    }

    public void clear() {
        content.clear();
    }

    public void show() {
        System.out.println(content);
    }
}
