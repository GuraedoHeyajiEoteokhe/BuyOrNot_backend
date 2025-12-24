package com.ambiguous.buyornot.charts.api.domain;

import com.ambiguous.buyornot.charts.api.controller.dto.CandleSingleResponse;
import com.ambiguous.buyornot.charts.api.controller.dto.response.CandleSeriesResponse;
import com.ambiguous.buyornot.charts.api.storage.CandleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;


@Service
@AllArgsConstructor
public class ChartService {

    private final CandleRepository candleRepository;

    @Transactional(readOnly = true)
    public CandleSeriesResponse getLatestCandles(Long stockId, String resolution, int count) {
        // 1. 최신 캔들 count 개를 가져온다.
        List<Candle> desc = candleRepository.findLatestCandle(stockId, resolution, PageRequest.of(0, count));

        // 2. 차트 오름차순 그리는 과정
        List<CandleSingleResponse> ascDtos = desc.stream()
                .sorted(Comparator.comparingLong(Candle::getTimeSec))
                .map(CandleSingleResponse::from)
                .toList();

        return new CandleSeriesResponse(stockId, resolution, ascDtos);
    }

    @Transactional(readOnly = true)
    public CandleSingleResponse getLatestBar(Long stockId, String resolution) {
        Candle latest = candleRepository.findTopByStockIdAndResolutionOrderByTimeSecDesc(stockId, resolution);

        if (latest == null) {
            throw new IllegalArgumentException("No candle found. stockId=" + stockId + ", resolution=" + resolution);
        }

        return CandleSingleResponse.from(latest);
    }
}
