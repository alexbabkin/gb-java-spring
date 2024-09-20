package com.github.alexbabkin.security.models;

import com.github.alexbabkin.security.dto.ProductDto;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class Cart {
    private List<ProductDto> products = new ArrayList<>();
}
