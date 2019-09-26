package com.devm8.stockalarms.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"stockid", "usersid"})})
public class Alarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "alarmname")
    private String alarmName;

    @Column(name = "isactive")
    private Boolean isActive;

    private BigDecimal price;

    @Column(name = "pricevariance")
    private BigDecimal priceVariance;

    @ManyToOne
    @JoinColumn(name = "stockid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Stock stock;

    @ManyToOne
    @JoinColumn(name = "usersid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MyUser user;

    public Alarm() {
    }

    public Alarm(String alarmName, Boolean isActive, BigDecimal price, BigDecimal priceVariance, Stock stock, MyUser user) {
        this.alarmName = alarmName;
        this.isActive = isActive;
        this.price = price;
        this.priceVariance = priceVariance;
        this.stock = stock;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceVariance() {
        return priceVariance;
    }

    public void setPriceVariance(BigDecimal priceVariance) {
        this.priceVariance = priceVariance;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }
}
