package com.github.alexbabkin.security.controllers;

import com.github.alexbabkin.security.dto.ProductDto;
import com.github.alexbabkin.security.exceptions.DataValidationException;
import com.github.alexbabkin.security.exceptions.ResourceNotFoundException;
import com.github.alexbabkin.security.models.Cart;
import com.github.alexbabkin.security.models.Category;
import com.github.alexbabkin.security.models.Product;
import com.github.alexbabkin.security.services.CategoryService;
import com.github.alexbabkin.security.services.ProductService;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final Cart cart;

    @GetMapping("/")
    public Page<ProductDto> findAll(
            @RequestParam(name = "page", defaultValue = "1") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        System.out.println(cart);
        return productService.findAll(pageIndex - 1, 10).map(ProductDto::new);
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return new ProductDto(
                productService
                        .findById(id)
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Product id = " + id + " not found")));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto save(
            @RequestBody @Validated ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(
                    bindingResult
                            .getAllErrors()
                            .stream()
                            .map(ObjectError::getDefaultMessage)
                            .collect(Collectors.toList()));
        }
        Product product = new Product();
        product.setId(productDto.getId());
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
        productService.save(product);
        return new ProductDto(product);
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody ProductDto productDto) {
        productService.updateProductFromDto(productDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PostMapping("/addToCart/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void addToCart(@PathVariable Long id) {
        cart.getProducts()
                .add(
                        new ProductDto(
                                productService
                                        .findById(id)
                                        .orElseThrow(
                                                () ->
                                                        new ResourceNotFoundException(
                                                                "Product id = "
                                                                        + id
                                                                        + " not found"))));
    }
}
