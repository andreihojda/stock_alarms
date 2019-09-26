package com.devm8.stockalarms.service;

import com.devm8.stockalarms.dao.AlarmDao;
import com.devm8.stockalarms.dao.StockDao;
import com.devm8.stockalarms.entities.Alarm;
import com.devm8.stockalarms.entities.Stock;
import com.devm8.stockalarms.pojo.GlobalQuote;
import com.devm8.stockalarms.pojo.StockOHLC;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

@Service
public class PollingService {

    @Value("${alphavantage.apikey}")
    private String apiKey;

    @Autowired
    AlarmDao alarmDao;

    @Autowired
    StockDao stockDao;

    @Autowired
    MailService mailService;

    public PollingService() {
    }

    @Scheduled(fixedDelayString = "${alphavantage.pollInterval}", initialDelayString = "${alphavantage.pollInterval}")
    public void scheduledPoll() {

        List<Stock> stocks = (List<Stock>) stockDao.findAll();
        stocks.forEach(stock -> {
            List<Alarm> alarms = alarmDao.findAllByStockAndIsActive(stock, true);
            if (alarms.size() > 0) {
                final StockOHLC stockOLHC = callPriceApi(stock.getTicker());

                alarms.forEach(alarm -> {
                    BigDecimal alarmPrice = alarm.getPrice();
                    BigDecimal currentPrice = stockOLHC.getPrice();
                    if (BigDecimal.ONE
                            .subtract(alarmPrice
                                    .divide(currentPrice, RoundingMode.HALF_UP))
                            .abs()
                            .multiply(new BigDecimal(100))
                            .compareTo(alarm.getPriceVariance()) > 0) {

                        mailService.sendMail(alarm.getUser().getEmail(), "Testing Stock Alerts", "Testing StockAlerts");
                        alarm.setActive(false);
                        alarmDao.save(alarm);
                    }
                });
            }
        });
    }


    public StockOHLC singlePoll(String ticker) {
        return callPriceApi(ticker);
    }

    private StockOHLC callPriceApi(String ticker) {
        final String uri = "https://www.alphavantage.co/query?" +
                "function=GLOBAL_QUOTE&" +
                "outputsize=compact&" +
                "symbol=MSFT&" +
                "apikey=" + apiKey;

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity request = new HttpEntity(null);
        HttpEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);
        String result = restTemplate.getForObject(uri, String.class);

        GlobalQuote globalQuote = null;
        try {
            globalQuote = new ObjectMapper().readValue(result, GlobalQuote.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(globalQuote).getStockOHLC();
    }

}
