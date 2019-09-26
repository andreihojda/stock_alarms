package com.devm8.stockalarms.controller;

import com.devm8.stockalarms.dao.AlarmDao;
import com.devm8.stockalarms.dao.StockDao;
import com.devm8.stockalarms.dao.UserDao;
import com.devm8.stockalarms.dto.AlarmDTO;
import com.devm8.stockalarms.dto.StockDTO;
import com.devm8.stockalarms.dto.UserDTO;
import com.devm8.stockalarms.entities.Alarm;
import com.devm8.stockalarms.entities.MyUser;
import com.devm8.stockalarms.entities.Stock;
import com.devm8.stockalarms.security.service.JwtService;
import com.devm8.stockalarms.security.service.JwtUserDetailsService;
import com.devm8.stockalarms.service.PollingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class StockAlarmController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private AlarmDao alarmDao;
    @Autowired
    private StockDao stockDao;
    @Autowired
    private PollingService pollingService;
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = {"/getAlarms"}, method = RequestMethod.GET)
    public Iterable<Alarm> firstPage() {
        return alarmDao.findAllByUser(getCurrentLoggedInUser());
    }

    @RequestMapping(value = "/addStock", method = RequestMethod.POST)
    public Stock addStock(@RequestBody StockDTO stockDto) {
        return stockDao.save(new Stock(stockDto.getStockTicker()));
    }

    @RequestMapping(value = "/editAlarm", method = RequestMethod.PUT)
    public Alarm editAlarm(@RequestBody AlarmDTO alarmDTO) {
        Alarm alarm = alarmDao.findById(alarmDTO.getId()).get();
        alarm.setActive(alarmDTO.getIsActive());
        alarm.setAlarmName(alarmDTO.getAlarmName());
        alarm.setPrice(alarmDTO.getPrice());
        alarm.setPriceVariance(alarmDTO.getPriceVariance());
        return alarmDao.save(alarm);
    }

    @RequestMapping(value = "/deleteAlarm", method = RequestMethod.DELETE)
    public void deleteAlarm(@RequestBody AlarmDTO alarmDTO) {
        alarmDao.deleteById(alarmDTO.getId());
    }

    @RequestMapping(value = "/getStocks", method = RequestMethod.GET)
    public Iterable<Stock> getStocks() {
        return stockDao.findAll();
    }

    @RequestMapping(value = "/setAlarm", method = RequestMethod.POST)
    public Alarm setAlarm(@RequestBody AlarmDTO alarmDTO) {
        Stock stock = stockDao.findByTicker(alarmDTO.getStockTicker());
        return alarmDao.save(new Alarm(alarmDTO.getAlarmName(), alarmDTO.getIsActive(), pollingService.singlePoll(stock.getTicker()).getPrice(),
                alarmDTO.getPriceVariance(), stock, getCurrentLoggedInUser()));
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody MyUser authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(token);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(user));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    private MyUser getCurrentLoggedInUser() {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return userDao.findByUsername(username);
    }
}