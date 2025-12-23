package com.ambiguous.buyornot.charts.api.controller.dto.response;


import com.ambiguous.buyornot.charts.api.controller.dto.CandleDto;

import java.util.List;

public record CandleSeriesResponse (
    Long stockId,
    String resolution,
    Long from,
    Long to,
    List<CandleDto> candles
) {}

