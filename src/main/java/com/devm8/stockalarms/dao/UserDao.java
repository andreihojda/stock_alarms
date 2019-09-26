package com.devm8.stockalarms.dao;

import com.devm8.stockalarms.entities.MyUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<MyUser, Long> {

    MyUser findByUsername(String username);
}
