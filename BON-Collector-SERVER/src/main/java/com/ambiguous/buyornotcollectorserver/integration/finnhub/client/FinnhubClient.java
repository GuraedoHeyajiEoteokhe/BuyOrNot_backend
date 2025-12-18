package com.ambiguous.buyornotcollectorserver.integration.finnhub.client;


import com.ambiguous.buyornotcollectorserver.integration.finnhub.dto.FinnhubCandleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class FinnhubClient {

    private final WebClient finnhubWebClient;

    public FinnhubCandleResponse getCandles(String symbol, String resolution, long from, long to) {
        return finnhubWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/stock/candle")
                        .queryParam("symbol", symbol)
                        .queryParam("resolution", resolution)
                        .queryParam("from", from)
                        .queryParam("to", to)
                        .build())
                .retrieve()
                .bodyToMono(FinnhubCandleResponse.class)
                .block();
    }
}
