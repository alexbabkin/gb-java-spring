package com.github.alexbabkin.security.dto;

import com.github.alexbabkin.security.models.Category;
import com.github.alexbabkin.security.models.Product;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String title;
    private List<Product> products;

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.title = category.getTitle();
        this.products = getProducts();
    }
}
