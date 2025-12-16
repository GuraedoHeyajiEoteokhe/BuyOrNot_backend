package com.ambiguous.buyornot.charts.repository;

import com.ambiguous.buyornot.charts.entity.Candle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandleRepository extends JpaRepository<Candle, Long> {

    List<Candle> findByStockIdAndResolutionAndTimeSecBetweenOrderByTimeSecAsc(
            Long stockId,
            String resolution,
            long from,
            long to
    );

    boolean existsByStockIdAndResolution(Long stockId, String resolution);
}
