package com.github.alexbabkin.webapp.controllers;

import com.github.alexbabkin.webapp.models.Product;
import com.github.alexbabkin.webapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductsController {
    private final ProductService service;

    @Autowired
    public ProductsController(ProductService service) {
        this.service = service;
    }

    @GetMapping(value = "/show_all")
    public String showProductsPage(Model model) {
        model.addAttribute("products", service.getAll());
        return "products";
    }

    @GetMapping(value = "/show/{id}")
    public String showProductsPageById(Model model, @PathVariable Long id) {
        model.addAttribute("product", service.findById(id));
        return "product_info";
    }

    @GetMapping(value = "/create")
    public String createProduct() {
        return "create_product";
    }

    @PostMapping(value = "/create")
    public String saveProduct(@RequestParam String title, @RequestParam Double cost) {
        service.save(new Product(service.getAll().size() + 1L, title, cost));
        return "redirect:/show_all";
    }

    @PostMapping(value = "/increase_cost/{id}")
    public String increaseCost(@PathVariable Long id) {
        var product = service.findById(id);
        product.setCost(product.getCost() + 1);
        service.save(product);
        return "redirect:/show/{id}";
    }

    @PostMapping(value = "/decrease_cost/{id}")
    public String decreaseCost(@PathVariable Long id) {
        var product = service.findById(id);
        var newCost = product.getCost() - 1;
        if (newCost > 0) {
            product.setCost(newCost);
            service.save(product);
        }
        return "redirect:/show/{id}";
    }
}
