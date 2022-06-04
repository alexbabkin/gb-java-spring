package com.github.alexbabkin.webapp.services;

import com.github.alexbabkin.webapp.dao.ProductDao;
import com.github.alexbabkin.webapp.models.Product;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Collection<Product> getAll() {
        return productDao.findAll();
    }

    public void save(Product product) {
        productDao.saveOrUpdate(product);
    }

    public Product findById(Long id) {
        return productDao.findById(id);
    }
}
