package com.github.alexbabkin.cart;

import com.github.alexbabkin.product.Product;
import com.github.alexbabkin.product.Repository;
import java.util.ArrayList;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Cart {
    private final ArrayList<Product> content = new ArrayList<>();

    private final Repository repository;

    @Autowired
    public Cart(Repository repository) {
        this.repository = repository;
    }

    public void add(Product product) {
        content.add(product);
    }

    public void add(Integer id) {
        repository
                .getAll()
                .stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .findAny()
                .ifPresent(this::add);
    }

    public void remove(Product product) {
        content.removeIf(p -> p.getId() == product.getId());
    }

    public void remove(Integer id) {
        content.removeIf(p -> Objects.equals(p.getId(), id));
    }

    public void clear() {
        content.clear();
    }

    public void show() {
        System.out.println(content);
    }
}
