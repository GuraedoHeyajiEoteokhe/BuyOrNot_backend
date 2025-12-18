package com.ambiguous.buyornotcollectorserver.job;


import com.ambiguous.buyornotcollectorserver.service.CandleCollectService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CandleCollectJob {

    private final CandleCollectService candleCollectService;

    @Scheduled(cron = "${collector.candle.cron}")
    public void run() {
        candleCollectService.collectOnce();
    }
}
