package com.ambiguous.buyornot.stock.repository;

import com.ambiguous.buyornot.stock.entity.Stock;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findByActiveTrue(Pageable pageable);

    List<Stock> findByActiveTrueAndExchange(String exchange, Pageable pageable);
}
