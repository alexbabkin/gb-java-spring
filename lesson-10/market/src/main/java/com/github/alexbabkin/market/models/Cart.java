package com.github.alexbabkin.market.models;

import com.github.alexbabkin.market.dto.ProductDto;
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
