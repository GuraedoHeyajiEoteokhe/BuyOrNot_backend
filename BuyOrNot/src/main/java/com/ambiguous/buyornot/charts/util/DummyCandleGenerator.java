package com.ambiguous.buyornot.charts.util;


import com.ambiguous.buyornot.charts.entity.Candle;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DummyCandleGenerator {

    private DummyCandleGenerator() {}

    public static List<Candle> generateMinuteCandles(
            Long stockId,
            String resolution,
            double basePrice
    ) {
        long now = Instant.now().getEpochSecond();
        long start = now - 3600; // 1시간
        Random r = new Random(stockId);

        List<Candle> list = new ArrayList<>();
        double price = basePrice;

        for (long t = start; t <= now; t += 60) {
            double open = price;
            double move = (r.nextDouble() - 0.5) * basePrice * 0.001;
            double close = Math.max(0.01, open + move);

            double high = Math.max(open, close);
            double low = Math.min(open, close);
            double volume = 100 + r.nextInt(900);

            list.add(Candle.builder()
                            .stockId(stockId)
                            .timeSec(t)
                            .resolution(resolution)
                            .openPrice(open)
                            .highPrice(high)
                            .lowPrice(low)
                            .closePrice(close)
                            .volume(volume)
                            .build());

            price = close;
        }
        return list;
    }
}
