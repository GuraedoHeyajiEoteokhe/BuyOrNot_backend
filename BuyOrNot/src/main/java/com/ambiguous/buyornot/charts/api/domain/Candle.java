package com.ambiguous.buyornot.charts.api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tbl_candle")
@Getter
@NoArgsConstructor
public class Candle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candleId;

    // FK를 id로만 매핑
    @Column(nullable=false)
    private Long stockId;

    // lightweight-charts에서는 epoch time 사용함.
    @Column(nullable = false)
    private Long timeSec;

    // 15분봉: "15", 1시간봉: "60" => 일단 15분봉이니 "15"
    @Column(nullable = false, length = 10)
    private String resolution;

    @Column(nullable = false)
    private Double open;

    @Column(nullable = false)
    private Double high;

    @Column(nullable = false)
    private Double low;

    @Column(nullable = false)
    private Double close;

    @Column(nullable = false)
    private Double volume;

    public static Candle create(
            Long stockId,
            Long timeSec,
            String resolution,
            Double open,
            Double high,
            Double low,
            Double close,
            Double volume
    ) {
        Candle candle = new Candle();
        candle.stockId = stockId;
        candle.timeSec = timeSec;
        candle.resolution = resolution;
        candle.open = open;
        candle.high = high;
        candle.low = low;
        candle.close = close;
        candle.volume = volume;
        return candle;
    }
}
