package com.github.alexbabkin.hiber2.services;

import com.github.alexbabkin.hiber2.dao.ProductDao;
import com.github.alexbabkin.hiber2.entities.Customer;
import com.github.alexbabkin.hiber2.entities.Product;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductService {
    private final ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Customer> getProductCustomers(Long productId) {
        return productDao.getProductCustomers(productId);
    }

    public List<Product> getAll() {
        return productDao.findAll();
    }
}
