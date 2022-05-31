package com.github.alexbabkin.springboot.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.alexbabkin.springboot.models.Product;
import com.github.alexbabkin.springboot.reporsitories.ProductRepository;

@Service
public class ProductService {

    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Collection<Product> getAll() {
        return repository.getAll();
    }

    public void save(Product product) {
        repository.save(product);
    }

    public Product findById(Long id) {
        return repository.findById(id);
    }
}
