package com.ambiguous.buyornot.stock.entity;

import com.ambiguous.buyornot.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Stock extends BaseEntity {

    @Column(nullable = false, unique = true, length = 40)
    private String symbol;

    /* 주식 종목 이름 */
    @Column(nullable = false, length = 120)
    private String name;

    // KRX, NASDAK
    @Column(nullable = false, length = 20)
    private String exchange;

    @Column(nullable = false)
    private boolean active = true;

    // 주식 종목 상태를 활성화 시킴.
    public static Stock active(String symbol, String name, String exchange) {
        return new Stock(symbol, name, exchange, true);
    }
}
