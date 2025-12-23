package com.ambiguous.buyornot.charts.repository;

import com.ambiguous.buyornot.charts.entity.Candle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CandleRepository extends JpaRepository<Candle, Long> {

    List<Candle> findByStockIdAndResolutionAndTimeEpochSecBetweenOrderByTimeEpochSecAsc(
            Long stockId,
            String resolution,
            long from,
            long to
    );

    Optional<Candle> findTopByStockIdAndResolutionOrderByTimeEpochSecDesc(Long stockId, String resolution);

    boolean existsByStockIdAndResolution(Long stockId, String resolution);
}
