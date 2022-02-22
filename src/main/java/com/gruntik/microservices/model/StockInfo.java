package com.gruntik.microservices.model;

import lombok.Getter;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;

@Getter
public class StockInfo {

    private final String stockCode;
    private final int stockCount;
    private final BigDecimal price;

    @ConstructorProperties({"stock_code", "stock_count", "price"})
    public StockInfo(String stockCode, int stockCount, BigDecimal price) {
        this.stockCode = stockCode;
        this.stockCount = stockCount;
        this.price = price;
    }
}
