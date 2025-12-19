package com.ambiguous.buyornotcollectorserver.service;


import com.ambiguous.buyornotcollectorserver.integration.finnhub.client.FinnhubClient;
import com.ambiguous.buyornotcollectorserver.integration.finnhub.dto.FinnhubCandleResponse;
import com.ambiguous.buyornotcollectorserver.repository.CandleWriteRepository;
import com.ambiguous.buyornotcollectorserver.repository.StockReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CandleCollectService {

    private final StockReadRepository stockReadRepository;
    private final CandleWriteRepository candleWriteRepository;
    private final FinnhubClient finnhubClient;

    @Value("${collector.candle.resolution}")
    private String resolution;

    @Value("${collector.candle.range-seconds}")
    private long rangeSeconds;

    public void collectOnce() {
        long to = Instant.now().getEpochSecond();
        long from = to - rangeSeconds;

        List<StockReadRepository.StockRow> stocks = stockReadRepository.findActiveStocks();

        for (StockReadRepository.StockRow stock : stocks) {
            FinnhubCandleResponse r = finnhubClient.getCandles(stock.symbol(), resolution, from, to);
            if (r == null || !r.isOk() || r.t() == null || r.t().isEmpty()) continue;

            int size = r.t().size();
            for (int i = 0; i < size; i++) {
                long t = r.t().get(i);
                candleWriteRepository.insertIgnore(
                        stock.stockId(),
                        t,
                        resolution,
                        safe(r.o(), i),
                        safe(r.h(), i),
                        safe(r.l(), i),
                        safe(r.c(), i),
                        safe(r.v(), i)
                );
            }
        }
    }

    private double safe(List<Double> list, int idx) {
        if (list == null || idx < 0 || idx >= list.size() || list.get(idx) == null) return 0.0;
        return list.get(idx);
    }
}
