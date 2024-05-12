package com.StockBroker.StockService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockServiceApplication.class, args);

		Client c  =  new Client();
		System.out.println(c.latestPrice("AAPL"));
	}

}
