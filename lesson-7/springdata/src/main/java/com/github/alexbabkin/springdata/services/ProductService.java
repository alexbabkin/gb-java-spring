package com.github.alexbabkin.springdata.services;

import com.github.alexbabkin.springdata.models.Product;
import com.github.alexbabkin.springdata.repositories.ProductRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    // public List<Product> getProducts(Integer min, Integer max) {
    //    if (min == null && max == null) {
    //        return productRepository.findAll();
    //    }
    //    if (min == null) {
    //        return productRepository.findProductsByPriceLessThanEqual(max);
    //    } else if (max == null) {
    //        return productRepository.findProductsByPriceGreaterThanEqual(min);
    //    } else {
    //        return productRepository.findProductBetweenPrices(min, max);
    //    }
    // }

    public Page<Product> getProducts(int pageIndex, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageIndex, pageSize));
    }
}
