package com.github.alexbabkin.security.services;

import com.github.alexbabkin.security.dto.ProductDto;
import com.github.alexbabkin.security.exceptions.ResourceNotFoundException;
import com.github.alexbabkin.security.models.Category;
import com.github.alexbabkin.security.models.Product;
import com.github.alexbabkin.security.repositories.ProductRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public Page<Product> findAll(int pageIndex, int pageSize) {
        return productRepository.findAll(
                PageRequest.of(pageIndex, pageSize, Sort.by("id").ascending()));
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void updateProductFromDto(ProductDto productDto) {
        Product product =
                findById(productDto.getId())
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Product id = "
                                                        + productDto.getId()
                                                        + " not found"));
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category =
                categoryService
                        .findByTitle(productDto.getCategoryTitle())
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Category title = "
                                                        + productDto.getCategoryTitle()
                                                        + " not found"));
        product.setCategory(category);
    }
}
