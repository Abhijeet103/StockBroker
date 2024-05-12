package com.StockBroker.StockService;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    Client client  =  new Client();
    @GetMapping("/stock/{ticker}")
    float getlatestPrice(@PathVariable  String ticker)
    {
        return client.latestPrice(ticker);
    }
}
