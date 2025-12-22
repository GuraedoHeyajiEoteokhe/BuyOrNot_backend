package com.ambiguous.buyornot.stock.controller;

import com.ambiguous.buyornot.stock.domain.StockService;
import com.ambiguous.buyornot.stock.dto.StockResponse;
import com.ambiguous.buyornot.stock.storage.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/stocks")
public class StockController {
    private final StockService stockService;

    @GetMapping
    public List<StockResponse> list (
            @RequestParam(required = false) String exchange,
            @RequestParam(defaultValue = "50") int limit
    ) {
        return stockService.listActive(exchange, limit);
    }
}
