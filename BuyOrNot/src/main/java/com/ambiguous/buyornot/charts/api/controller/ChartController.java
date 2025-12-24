package com.ambiguous.buyornot.charts.api.controller;


import com.ambiguous.buyornot.charts.api.controller.dto.CandleDto;
import com.ambiguous.buyornot.charts.api.controller.dto.response.CandleSeriesResponse;
import com.ambiguous.buyornot.charts.api.domain.ChartService;
import com.ambiguous.buyornot.common.support.response.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Charts", description="차트 조회 API - Domain server")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chart")
public class ChartController {

    private final ChartService chartService;

    @Operation(
            summary = "캔들 시리즈 조회(차트용)",
            description = """
                    StockId + resolution 기준으로 최신 캔들 count개를 조회한다.
                    - 반환 candles는 time 오름차순으로 정렬되어 차트에 바로 넣기 쉬움
                    """
    )
    @GetMapping("/candles")
    public ApiResult<CandleSeriesResponse> getCandles(
            @RequestParam Long stockId,
            @RequestParam(defaultValue = "15") String resolution,
            @RequestParam(defaultValue = "200") int count
    ) {
        return ApiResult.success(chartService.getLatestCandles(stockId, resolution, count));
    }

}
