package com.github.alexbabkin.market.dto;

import com.github.alexbabkin.market.models.Category;
import com.github.alexbabkin.market.models.Product;
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
