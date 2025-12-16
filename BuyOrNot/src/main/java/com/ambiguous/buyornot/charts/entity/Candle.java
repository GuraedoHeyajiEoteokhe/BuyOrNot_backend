package com.ambiguous.buyornot.charts.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tbl_candle")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Candle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candleId;

    // FK
    @Column(nullable=false)
    private Long stockId;

    // lightweight-charts: epoch seconds
    @Column(nullable = false)
    private Long timeSec;

    // 예: "1"(1m), "5"(5m), "D"(day) 등
    @Column(nullable = false, length = 10)
    private String resolution;

    @Column(nullable = false)
    private Double openPrice;

    @Column(nullable = false)
    private Double highPrice;

    @Column(nullable = false)
    private Double lowPrice;

    @Column(nullable = false)
    private Double closePrice;

    @Column(nullable = false)
    private Double volume;

    public static Candle create(
            Long stockId,
            Long timeSec,
            String resolution,
            Double openPrice,
            Double highPrice,
            Double lowPrice,
            Double closePrice,
            Double volume
    ) {
        Candle candle = new Candle();
        candle.stockId = stockId;
        candle.timeSec = timeSec;
        candle.resolution = resolution;
        candle.openPrice = openPrice;
        candle.highPrice = highPrice;
        candle.lowPrice = lowPrice;
        candle.closePrice = closePrice;
        candle.volume = volume;
        return candle;
    }
}
