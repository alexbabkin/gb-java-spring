package com.github.alexbabkin.springdata.controlles;

import com.github.alexbabkin.springdata.models.Product;
import com.github.alexbabkin.springdata.services.ProductService;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductRestController {
    private final ProductService productService;

    @GetMapping("/products/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getById(id).orElse(null);
    }

    @GetMapping("/products")
    public List<Product> getProducts(
            @RequestParam("min_price") Optional<Integer> minPrice,
            @RequestParam("max_price") Optional<Integer> maxPrice) {
        return productService.getProducts(minPrice.orElse(null), maxPrice.orElse(null));
    }

    @GetMapping("/products/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping("/products/products")
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }
}
