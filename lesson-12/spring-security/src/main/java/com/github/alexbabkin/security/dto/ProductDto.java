package com.github.alexbabkin.security.dto;

import com.github.alexbabkin.security.models.Product;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;

    @NotNull(message = "The product must have a name")
    @Length(
            min = 3,
            max = 255,
            message = "The length of the product name should be 3-255 characters")
    private String title;

    @Min(value = 1, message = "The price of the product must be at least 1 rub")
    private int price;

    @NotNull(message = "The product must have a category")
    private String categoryTitle;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.categoryTitle = product.getCategory().getTitle();
    }
}
