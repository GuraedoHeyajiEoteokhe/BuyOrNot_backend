package com.ambiguous.buyornotcollectorserver.config;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/* application.yml 세팅값 받아오는 record*/

@Validated
@ConfigurationProperties(prefix = "collector")
public record CollectorProperty (
    @Valid Finnhub finnhub,
    @Valid Stock stock
)   {

    public record Finnhub(
            @NotBlank String baseUrl,
            //token 은 환경변수로 들어오므로 null일 수 있음 -> 런타임에서 체크해서 예외처리
            String token,
            @Valid Candle candle
    ) {}

    public record Candle(
            @NotBlank String resolution,
            @Positive long rangeSeconds
    ) {}

    public record Stock(
            boolean preferActiveFilter
    ) {}
}
