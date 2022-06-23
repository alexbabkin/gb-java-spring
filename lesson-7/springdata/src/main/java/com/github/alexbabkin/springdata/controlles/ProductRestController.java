package com.github.alexbabkin.springdata.controlles;

import com.github.alexbabkin.springdata.models.Product;
import com.github.alexbabkin.springdata.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductRestController {
    private final ProductService productService;

    @GetMapping("/products/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getById(id).orElse(null);
    }

    // @GetMapping("/products")
    // public List<Product> getProducts(
    //        @RequestParam("min_price") Optional<Integer> minPrice,
    //        @RequestParam("max_price") Optional<Integer> maxPrice) {
    //    var q = productService.getProducts(minPrice.orElse(null), maxPrice.orElse(null));
    //    return productService.getProducts(minPrice.orElse(null), maxPrice.orElse(null));
    // }

    @GetMapping("/products")
    public Page<Product> getProducts(@RequestParam(name = "p", defaultValue = "1") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return productService.getProducts(pageIndex - 1, 10);
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
