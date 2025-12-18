package com.ambiguous.buyornotcollectorserver.integration.finnhub.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FinnhubCandleResponse(
        String s,
        List<Long> t,
        List<Double> o,
        List<Double> h,
        List<Double> l,
        List<Double> c,
        List<Double> v
) {
    public boolean isOk() { return "ok".equalsIgnoreCase(s); }
}
