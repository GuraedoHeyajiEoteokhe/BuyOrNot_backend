package com.ambiguous.buyornot.stock.storage;

import com.ambiguous.buyornot.stock.domain.Stock;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {

    List<Stock> findByExchangeOrderBySymbolAsc(String exchange);

    // hotpost Query
    @Query("select s.symbol from Stock s where s.id = :id")
    String findSymbolById(@Param("id") Long id);
}
