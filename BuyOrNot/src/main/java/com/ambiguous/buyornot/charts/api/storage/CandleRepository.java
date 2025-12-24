package com.ambiguous.buyornot.charts.api.storage;

import com.ambiguous.buyornot.charts.api.domain.Candle;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandleRepository extends JpaRepository<Candle, Long> {

    List<Candle> findLatestCandle(Long stockId, String resolution, PageRequest of);

    Candle findTopByStockIdAndResolutionOrderByTimeSecDesc(Long stockId, String resolution);
}
