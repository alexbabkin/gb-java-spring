package ru.gb.MyMarket.market.models;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.MyMarket.market.dto.ProductDto;

@Component
@Data
@NoArgsConstructor
public class Cart {
    private List<ProductDto> products = new ArrayList<>();
}
