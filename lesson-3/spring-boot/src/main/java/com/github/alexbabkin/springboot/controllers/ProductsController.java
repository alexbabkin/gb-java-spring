package com.github.alexbabkin.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.alexbabkin.springboot.models.Product;
import com.github.alexbabkin.springboot.services.ProductService;

@Controller
public class ProductsController {
    private final ProductService service;

    @Autowired
    public ProductsController(ProductService service) {
        this.service = service;
    }

    @GetMapping(value = "/show_all")
    public String showProductsPage(Model model){
        model.addAttribute("products", service.getAll());
        return "products";
    }

    // GET http://localhost:8189/app/show/{id}
    @GetMapping(value = "/show/{id}")
    public String showProductsPageById(Model model, @PathVariable Long id){
        model.addAttribute("product", service.findById(id));
        return "product_info";
    }

    @GetMapping(value = "/create")
    public String createProduct(){
        return "create_product";
    }

    @PostMapping(value = "/create")
    public String saveProduct(@RequestParam String title, @RequestParam Double cost){
        service.save(new Product(service.getAll().size() + 1L, title, cost));
        return "redirect:/show_all";
    }

}
