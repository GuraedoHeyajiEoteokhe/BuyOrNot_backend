package com.ambiguous.buyornotcollectorserver.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CandleWriteRepository {

    private final JdbcTemplate jdbc;

    // MariaDB/MySQL: INSERT IGNORE 사용 (중복키면 무시)
    public void insertIgnore(
            long stockId, long timeEpochSec, String resolution,
            double o, double h, double l, double c, double v
    ) {
        String sql = """
            INSERT IGNORE INTO candle
            (stock_id, time_epoch_sec, resolution, open_price, high_price, low_price, close_price, volume)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;
        jdbc.update(sql, stockId, timeEpochSec, resolution, o, h, l, c, v);
    }
}
