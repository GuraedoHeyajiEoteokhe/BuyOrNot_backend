package com.ambiguous.buyornotcollectorserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class FinnhubClientConfig {

    @Bean
    public WebClient finnhubWebClient(
            @Value("${collector.finnhub.base-url}") String baseUrl,
            @Value("${collector.finnhub.token:}") String token
    ) {
        if (baseUrl == null || baseUrl.isBlank()) {
            throw new IllegalStateException("collector.finnhub.base-url is empty");
        }
        if (token == null || token.isBlank()) {
            throw new IllegalStateException("collector.finnhub.token is empty (set FINNHUB_TOKEN env or yml)");
        }

        return WebClient.builder()
                .baseUrl(baseUrl.trim())
                .defaultHeader("X-Finnhub-Token", token.trim())
                .build();
    }
}
