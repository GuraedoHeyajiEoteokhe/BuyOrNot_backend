package com.ambiguous.buyornot.charts.service;

import com.ambiguous.buyornot.charts.dto.CandleDto;
import com.ambiguous.buyornot.charts.dto.response.CandleSeriesResponse;
import com.ambiguous.buyornot.charts.entity.Candle;
import com.ambiguous.buyornot.charts.repository.CandleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

import static com.ambiguous.buyornot.charts.dto.CandleDto.toDto;

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
                        .findByStockIdAndResolutionAndTimeEpochSecBetweenOrderByTimeEpochSecAsc(
                                stockId, res, f, t
                        )
                        .stream()
                        .map(CandleDto::toDto)
                        .toList();

        return CandleSeriesResponse.builder()
                                .stockId(stockId)
                                .resolution(res)
                                .from(f)
                                .to(t)
                                .candles(candles).build();
    }

    @Transactional(readOnly = true)
    public CandleDto latestBar(Long stockId, String resolution) {
        String res = normalizeResolution(resolution);

        Candle candle = candleRepository
                .findTopByStockIdAndResolutionOrderByTimeEpochSecDesc(stockId, res)
                .orElseThrow(() -> new IllegalArgumentException("해당 종목의 캔들 데이터가 존재하지 않습니다."));

        return toDto(candle);
    }


    private String normalizeResolution(String r) {
        return (r == null || r.isBlank()) ? "1" : r.trim();
    }
}
