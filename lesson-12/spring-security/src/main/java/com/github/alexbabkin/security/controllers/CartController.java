package com.github.alexbabkin.security.controllers;

import com.github.alexbabkin.security.dto.ProductDto;
import com.github.alexbabkin.security.models.Cart;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {
    private final Cart cart;

    @GetMapping("/")
    public List<ProductDto> findAll() {
        return cart.getProducts();
    }

    @PostMapping("/")
    public void deleteProductFromCart(@RequestBody ProductDto product) {
        cart.getProducts().remove(product);
    }
}
