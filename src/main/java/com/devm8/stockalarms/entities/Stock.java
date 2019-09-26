package com.devm8.stockalarms.entities;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ticker;

    @Transient
    private Set<Alarm> alarms;

    public Stock(){

    }

    public Stock(String stockTicker) {
        this.ticker = stockTicker;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    @OneToMany(mappedBy = "stock")
    public Set<Alarm> getAlarms() {
        return alarms;
    }

    public void setAlarms(Set<Alarm> alarms) {
        this.alarms = alarms;
    }
}
