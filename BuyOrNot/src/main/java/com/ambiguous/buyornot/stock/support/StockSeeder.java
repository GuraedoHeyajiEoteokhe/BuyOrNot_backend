package com.ambiguous.buyornot.stock.support;

import com.ambiguous.buyornot.stock.domain.Stock;
import com.ambiguous.buyornot.stock.storage.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Order(2)
@RequiredArgsConstructor
@Component
public class StockSeeder implements ApplicationRunner {
    private final StockRepository stockRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (stockRepository.count() == 0) return;

        stockRepository.saveAll(List.of(
                Stock.active("AAPL", "Apple Inc.", "NASDAQ"),
                Stock.active("MSFT", "Microsoft Corp.", "NASDAQ"),
                Stock.active("NVDA", "NVIDIA Corp.", "NASDAQ"),
                Stock.active("AMZN", "Amazon.com Inc.", "NASDAQ"),
                Stock.active("GOOGL", "Alphabet Inc.", "NASDAQ"),
                Stock.active("TSLA", "Tesla Inc.", "NASDAQ"),
                Stock.active("META", "Meta Platforms Inc.", "NASDAQ"),
                Stock.active("JPM", "JPMorgan Chase & Co.", "NYSE"),
                Stock.active("KO", "Coca-Cola Co.", "NYSE"),
                Stock.active("XOM", "Exxon Mobil Corp.", "NYSE")
        ));
    }
}
