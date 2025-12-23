package com.ambiguous.buyornot.stock.domain;

import com.ambiguous.buyornot.stock.controller.dto.request.StockRequest;
import com.ambiguous.buyornot.stock.controller.dto.response.StockResponse;
import com.ambiguous.buyornot.stock.storage.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StockService {
    private final StockRepository stockRepository;

    // 주식 종목 
    public List<StockResponse> getStocksByExchange(String exchange) {
        List<Stock> stocks = stockRepository.findByExchange(exchange);
        return stocks.stream()
                .map(StockResponse::from)
                .toList();
    }
}
