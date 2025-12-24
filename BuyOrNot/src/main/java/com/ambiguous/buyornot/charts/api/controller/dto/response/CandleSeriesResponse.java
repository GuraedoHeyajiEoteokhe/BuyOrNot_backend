package com.ambiguous.buyornot.charts.api.controller.dto.response;

import com.ambiguous.buyornot.charts.api.controller.dto.CandleSingleResponse;

import java.util.List;

public record CandleSeriesResponse (
        Long stockId,
        String resolution,
        List<CandleSingleResponse> candles
) {

}
