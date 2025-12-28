package com.ambiguous.buyornotcollectorserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties(CollectorProperty.class)
public class FinnhubClientConfig {

    @Bean
    public WebClient FinnhubWebClient(CollectorProperty collectorProperty) {
        return WebClient.builder()
                .baseUrl(collectorProperty.finnhub().baseUrl())
                .build();
    }
}
