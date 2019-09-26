package com.devm8.stockalarms;

import com.devm8.stockalarms.service.PollingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableScheduling
@ComponentScan("com.devm8.stockalarms.*")
public class StockAlarmsApplication {

	@Autowired
	private PollingService pollingService;

	public static void main(String[] args) {
		SpringApplication.run(StockAlarmsApplication.class, args);
	}

}
