package com.ambiguous.buyornot.stock.controller;

import com.ambiguous.buyornot.common.support.error.CoreException;
import com.ambiguous.buyornot.common.support.error.ErrorCode;
import com.ambiguous.buyornot.common.support.error.ErrorMessage;
import com.ambiguous.buyornot.common.support.error.ErrorType;
import com.ambiguous.buyornot.common.support.response.ApiResult;
import com.ambiguous.buyornot.stock.controller.dto.request.StockRequest;
import com.ambiguous.buyornot.stock.domain.StockService;
import com.ambiguous.buyornot.stock.controller.dto.response.StockResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name="Stock", description="Stock 종목 조회 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/stocks")
public class StockController {

    private final StockService stockService;

    @Operation(summary="종목 목록 조회",
    description= """
            DB(stock 테이블)에 저장된 종목 목록을 조회한다.
            - exchange 파라미터가 없으면 전체 목록
            - exchange 파라미터가 있으면 해당 거래소만 필터링""")
    @GetMapping
    public ApiResult<List<StockResponse>> getStocks(@RequestParam String exchange) {
        if (exchange == null || exchange.isBlank()) {
            throw new CoreException(ErrorType.DEFAULT_ARGUMENT_NOT_VALID_ISNULL);
        }
        return ApiResult.success(stockService.getStocksByExchange(exchange));

    }
}
