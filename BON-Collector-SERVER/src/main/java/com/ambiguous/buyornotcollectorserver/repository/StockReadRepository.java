package com.ambiguous.buyornotcollectorserver.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StockReadRepository {

    private final JdbcTemplate jdbc;

    public List<StockRow> findActiveStocks() {
        String sql = """
            SELECT id, symbol
            FROM stock
            WHERE active = true
        """;
        return jdbc.query(sql, (rs, rowNum) ->
                new StockRow(
                        rs.getLong("id"),
                        rs.getString("symbol")
                )
        );
    }

    public record StockRow(Long stockId, String symbol) {}
}
