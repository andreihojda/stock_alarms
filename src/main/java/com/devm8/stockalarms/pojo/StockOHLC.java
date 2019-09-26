package com.devm8.stockalarms.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class StockOHLC {

    @JsonProperty(value = "01. symbol")
    private String symbol;
    @JsonProperty(value = "02. open")
    private String open;
    @JsonProperty(value = "03. high")
    private String high;
    @JsonProperty(value = "04. low")
    private String low;
    @JsonProperty(value = "05. price")
    private BigDecimal price;
    @JsonProperty(value = "06. volume")
    private String volume;
    @JsonProperty(value = "07. latest trading day")
    private String lastTradingDay;
    @JsonProperty(value = "08. previous close")
    private String lastClose;
    @JsonProperty(value = "09. change")
    private String change;
    @JsonProperty(value = "10. change percent")
    private String changePercent;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getLastTradingDay() {
        return lastTradingDay;
    }

    public void setLastTradingDay(String lastTradingDay) {
        this.lastTradingDay = lastTradingDay;
    }

    public String getLastClose() {
        return lastClose;
    }

    public void setLastClose(String lastClose) {
        this.lastClose = lastClose;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getChangePercent() {
        return changePercent;
    }

    public void setChangePercent(String changePercent) {
        this.changePercent = changePercent;
    }
}
