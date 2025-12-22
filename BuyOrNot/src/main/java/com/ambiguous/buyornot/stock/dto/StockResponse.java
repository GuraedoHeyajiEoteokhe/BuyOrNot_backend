package com.ambiguous.buyornot.stock.dto;

import com.ambiguous.buyornot.stock.entity.Stock;

public record StockResponse(
        Long id,
        String symbol,
        String name,
        String exchange
) {
    public static StockResponse from(Stock s) {
        return new StockResponse(s.getId(), s.getSymbol(), s.getName(), s.getExchange());
    }
}
