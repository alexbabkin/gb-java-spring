package ru.gb.market.exceptions;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MarketError {
    private String message;
    private Date date;

    public MarketError(String message) {
        this.message = message;
        this.date = new Date();
    }
}
