package com.ambiguous.buyornot.charts.dto.response;

import com.ambiguous.buyornot.charts.dto.CandleDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CandleSeriesResponse {
    private Long stockId;
    private String resolution;
    private Long from;
    private Long to;
    private List<CandleDto> candles;
}
