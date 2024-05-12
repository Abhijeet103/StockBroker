package com.StockBroker.StockService;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;

public class Client {

    WebClient client ;
    String API_KEY ="fced443141e501d554d0b38c4a34bba085172b1e";

    Client()
    {
        client =  WebClient.create();
    }

    float latestPrice(String ticker)
    {
        String url  = "https://api.tiingo.com/tiingo/daily/%s/prices?token=%s";
        url  = String.format(url , ticker , API_KEY);
        List<Stock> stock  = client.get().uri(url).retrieve().bodyToMono(new ParameterizedTypeReference<List<Stock>>() {
        }).block();
        return stock.get(0).getAdjClose();

    }
}
