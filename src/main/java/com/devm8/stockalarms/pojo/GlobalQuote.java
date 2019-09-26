package com.devm8.stockalarms.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GlobalQuote {

    @JsonProperty(value = "Global Quote")
    private StockOHLC stockOHLC;

    public StockOHLC getStockOHLC() {
        return stockOHLC;
    }

    public void setStockOHLC(StockOHLC stockOHLC) {
        this.stockOHLC = stockOHLC;
    }
}
