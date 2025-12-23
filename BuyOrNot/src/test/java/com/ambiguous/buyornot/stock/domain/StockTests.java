package com.ambiguous.buyornot.stock.domain;


import com.ambiguous.buyornot.stock.controller.StockController;
import com.ambiguous.buyornot.stock.controller.dto.response.StockResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StockController.class)
class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String BASE = "/api/v1/stocks";

    @MockBean
    private StockService stockService;

    @Test
    @DisplayName("종목 단건 조회 성공")
    void getStockById_success() throws Exception {
        // given
        Long stockId = 1L;

        StockResponse response = new StockResponse(
                stockId,
                "AAPL",
                "Apple Inc.",
                "NASDAQ"
        );

        given(stockService.getStockByStockId(stockId))
                .willReturn(response);

        // when & then
        mockMvc.perform(get(BASE + "/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.symbol").value("AAPL"))
                .andExpect(jsonPath("$.data.name").value("Apple Inc."))
                .andExpect(jsonPath("$.data.exchange").value("NASDAQ"));

        verify(stockService).getStockByStockId(stockId);
    }

    @Test
    @DisplayName("거래소 기준 종목 목록 조회 성공")
    void getStocksByExchange_success() throws Exception {
        // given
        String exchange = "NASDAQ";

        List<StockResponse> responses = List.of(
                new StockResponse(1L, "AAPL", "Apple Inc.", "NASDAQ"),
                new StockResponse(2L, "MSFT", "Microsoft", "NASDAQ")
        );

        given(stockService.getStocksByExchange(exchange))
                .willReturn(responses);

        // when & then
        mockMvc.perform(get(BASE)
                        .param("exchange", exchange))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.data[0].symbol").value("AAPL"))
                .andExpect(jsonPath("$.data[1].symbol").value("MSFT"));

        verify(stockService).getStocksByExchange(exchange);
    }

}
