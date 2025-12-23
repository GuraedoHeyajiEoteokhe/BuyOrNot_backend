package com.ambiguous.buyornot.charts.api.controller.dto;

import com.ambiguous.buyornot.charts.api.domain.Candle;

public record CandleDto (
    Long time,
    Double open,
    Double high,
    Double low,
    Double close
    ){
        public static CandleDto from(Candle c) {
        return new CandleDto(c.getTimeSec(),c.getOpen(),c.getHigh(),c.getLow(),c.getClose());
    }
}