package com.ambiguous.buyornot.charts.api.domain;

import com.ambiguous.buyornot.charts.api.controller.dto.CandleDto;
import com.ambiguous.buyornot.charts.api.controller.dto.response.CandleSeriesResponse;
import com.ambiguous.buyornot.charts.api.storage.CandleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

import static com.ambiguous.buyornot.charts.api.controller.dto.CandleDto.toDto;

@Service
public class ChartService {

    private final CandleRepository candleRepository;

    public ChartService(CandleRepository candleRepository) {
        this.candleRepository = candleRepository;
    }

    @Transactional(readOnly = true)
    public CandleSeriesResponse candles(
            Long stockId,
            String resolution,
            Long from,
            Long to
    ) {
        String res = normalizeResolution(resolution);
        long now = Instant.now().getEpochSecond();

        long f = (from == null) ? now - 3600 : from;
        long t = (to == null) ? now : to;

        List<CandleDto> candles =
                candleRepository
                        .findByStockIdAndResolutionAndTimeSecBetweenOrderByTimeSecAsc(
                                stockId, res, f, t
                        )
                        .stream()
                        .map(CandleDto::from)
                        .toList();

        return CandleSeriesResponse.builder()
                                .stockId(stockId)
                                .resolution(res)
                                .from(f)
                                .to(t)
                                .candles(candles).build();
    }

    @Transactional
    public CandleDto latestBar(Long stockId, String resolution) {
        String res = normalizeResolution(resolution);
        long now = Instant.now().getEpochSecond();

        double open = 100 + Math.random() * 10;
        double close = open + (Math.random() - 0.5);
        double high = Math.max(open, close);
        double low = Math.min(open, close);
        double volume = 50 + Math.random() * 200;

        // ChartService.java
        Candle candle = Candle.create(
                stockId, now, res,
                open, high, low, close, volume
        );

        candleRepository.save(candle);
        return toDto(candle);
    }


    private String normalizeResolution(String r) {
        return (r == null || r.isBlank()) ? "1" : r.trim();
    }
}
