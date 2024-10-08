package com.github.alexbabkin.security.controllers;

import com.github.alexbabkin.security.dto.CategoryDto;
import com.github.alexbabkin.security.services.CategoryService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/")
    public List<CategoryDto> findAll() {
        return categoryService
                .findAll()
                .stream()
                .map(CategoryDto::new)
                .collect(Collectors.toList());
    }
}
