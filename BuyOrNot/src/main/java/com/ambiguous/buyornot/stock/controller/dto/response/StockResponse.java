package com.ambiguous.buyornot.stock.controller.dto.response;

import com.ambiguous.buyornot.stock.domain.Stock;

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
