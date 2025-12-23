package com.ambiguous.buyornot.charts.api.controller.dto;

import com.ambiguous.buyornot.charts.api.domain.Candle;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CandleDto {
    private Long time;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Double volume;

    public static CandleDto toDto(Candle candle) {
        return CandleDto.builder()
                .time(candle.getTimeSec())
                .open(candle.getOpenPrice())
                .high(candle.getHighPrice())
                .low(candle.getLowPrice())
                .close(candle.getClosePrice())
                .volume(candle.getVolume())
                .build();
    }
}