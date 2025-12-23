package com.ambiguous.buyornot.charts.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "candle")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Candle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candleId;

    @Column(name = "stock_id", nullable = false)
    private Long stockId;

    // lightweight-charts: epoch seconds
    @Column(name = "time_epoch_sec", nullable = false)
    private Long timeEpochSec;

    // 예: "1"(1m), "5"(5m), "D"(day) 등
    @Column(nullable = false, length = 10)
    private String resolution;

    @Column(name = "open_price", nullable = false)
    private Double openPrice;

    @Column(name = "high_price", nullable = false)
    private Double highPrice;

    @Column(name = "low_price", nullable = false)
    private Double lowPrice;

    @Column(name = "close_price", nullable = false)
    private Double closePrice;

    @Column(nullable = false)
    private Double volume;

    public static Candle create(
            Long stockId,
            Long timeEpochSec,
            String resolution,
            Double openPrice,
            Double highPrice,
            Double lowPrice,
            Double closePrice,
            Double volume
    ) {
        Candle candle = new Candle();
        candle.stockId = stockId;
        candle.timeEpochSec = timeEpochSec;
        candle.resolution = resolution;
        candle.openPrice = openPrice;
        candle.highPrice = highPrice;
        candle.lowPrice = lowPrice;
        candle.closePrice = closePrice;
        candle.volume = volume;
        return candle;
    }
}
