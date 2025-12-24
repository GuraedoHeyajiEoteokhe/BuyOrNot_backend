package com.ambiguous.buyornot.charts.api.controller.dto;

import com.ambiguous.buyornot.charts.api.domain.Candle;

public record CandleSingleResponse(
    Long time,
    Double open,
    Double high,
    Double low,
    Double close
    ){
        public static CandleSingleResponse from(Candle c) {
        return new CandleSingleResponse(c.getTimeSec(),c.getOpen(),c.getHigh(),c.getLow(),c.getClose());
    }
}