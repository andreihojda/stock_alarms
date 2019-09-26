package com.devm8.stockalarms.dao;


import com.devm8.stockalarms.entities.Alarm;
import com.devm8.stockalarms.entities.MyUser;
import com.devm8.stockalarms.entities.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlarmDao extends CrudRepository<Alarm, Long> {

    public List<Alarm> findAllByStockAndIsActive(Stock stock, Boolean isActive);

    public List<Alarm> findAllByUser(MyUser user);
}
