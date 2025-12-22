package com.ambiguous.buyornot.stock.domain;

import com.ambiguous.buyornot.stock.dto.StockResponse;
import com.ambiguous.buyornot.stock.storage.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StockService {
    private final StockRepository stockRepository;

    public List<StockResponse> listActive(String exchange, int limit) {
        var pageable = PageRequest.of(0, Math.max(1, Math.min(limit, 100)));

        var list = (exchange == null || exchange.isBlank())
                ? stockRepository.findByActiveTrue(pageable)
                : stockRepository.findByActiveTrueAndExchange(exchange, pageable);

        return list.stream().map(StockResponse::from).toList();
    }
}
