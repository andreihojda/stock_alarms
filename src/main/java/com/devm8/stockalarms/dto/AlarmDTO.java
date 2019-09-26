package com.devm8.stockalarms.dto;

import com.devm8.stockalarms.entities.Stock;

import java.math.BigDecimal;

public class AlarmDTO {

    private Long id;

    private String alarmName;

    private Boolean isActive;

    private BigDecimal price;

    private BigDecimal priceVariance;

    private String stockTicker;

    public BigDecimal getPriceVariance() {
        return priceVariance;
    }

    public void setPriceVariance(BigDecimal priceVariance) {
        this.priceVariance = priceVariance;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public String getStockTicker() {
        return stockTicker;
    }

    public void setStockTicker(String stockTicker) {
        this.stockTicker = stockTicker;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
