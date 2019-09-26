package com.devm8.stockalarms.dao;

import com.devm8.stockalarms.entities.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockDao extends CrudRepository<Stock, Long> {

    Stock findByTicker(String stockTicker);
}
