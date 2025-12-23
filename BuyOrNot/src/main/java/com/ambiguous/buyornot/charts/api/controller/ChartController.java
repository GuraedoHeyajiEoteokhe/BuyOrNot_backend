package com.ambiguous.buyornot.charts.api.controller;


import com.ambiguous.buyornot.charts.api.controller.dto.CandleDto;
import com.ambiguous.buyornot.charts.api.controller.dto.response.CandleSeriesResponse;
import com.ambiguous.buyornot.charts.api.domain.ChartService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/charts")
public class ChartController {

    private final ChartService chartService;

    public ChartController(ChartService chartService) {
        this.chartService = chartService;
    }

    // /api/charts/candles?stockId=1&resolution=1
    @GetMapping("/candles")
    public CandleSeriesResponse candles(
            @RequestParam Long stockId,
            @RequestParam(required = false) String resolution,
            @RequestParam(required = false) Long from,
            @RequestParam(required = false) Long to
    ) {
        return chartService.candles(stockId, resolution, from, to);
    }

    // /api/charts/latest-bar?stockId=1
    @GetMapping("/latest-bar")
    public CandleDto latestBar(
            @RequestParam Long stockId,
            @RequestParam(required = false) String resolution
    ) {
        return chartService.latestBar(stockId, resolution);
    }
}
