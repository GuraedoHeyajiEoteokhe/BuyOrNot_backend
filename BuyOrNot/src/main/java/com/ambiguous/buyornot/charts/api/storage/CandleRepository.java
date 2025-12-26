package com.ambiguous.buyornot.charts.api.storage;

import com.ambiguous.buyornot.charts.api.domain.Candle;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CandleRepository extends JpaRepository<Candle, Long> {

    // 최신 캔들을 1개 들고온다.-Cont:
    Candle findTopByStockIdAndResolutionOrderByTimeSecDesc(Long stockId, String resolution);

    // 최신 캔들을 count 수 만큼 들고온다.
    @Query("""
           SELECT c
           FROM Candle c
           WHERE c.stockId = :stockId
             AND c.resolution = :resolution
           ORDER BY c.timeSec DESC
           """)
    List<Candle> findLatestCandle(Long stockId, String resolution, PageRequest of);
}
