package com.ambiguous.buyornot.stock.domain;

import com.ambiguous.buyornot.common.support.error.CoreException;
import com.ambiguous.buyornot.common.support.error.ErrorMessage;
import com.ambiguous.buyornot.common.support.error.ErrorType;
import com.ambiguous.buyornot.stock.controller.dto.request.StockRequest;
import com.ambiguous.buyornot.stock.controller.dto.response.StockResponse;
import com.ambiguous.buyornot.stock.storage.StockRepository;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StockService {
    private final StockRepository stockRepository;

    // 주식 종목 목록 조회
    public List<StockResponse> getStocksByExchange(String exchange) {
        List<Stock> stocks = stockRepository.findByExchangeOrderBySymbolAsc(exchange);
        return stocks.stream()
                .map(StockResponse::from)
                .toList();
    }

    // 주식 종목 단일 조회
    public StockResponse getStockByStockId(Long id) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new CoreException(ErrorType.DEFAULT_ERROR_FIND_ERROR));
        return StockResponse.from(stock);
    }
}
