package com.StockBroker.broker.service.service;

import com.StockBroker.broker.service.dto.ServiceDto;
import com.StockBroker.broker.service.dto.UserDto;
import com.StockBroker.broker.service.entity.StockOption;
import com.StockBroker.broker.service.entity.User;
import com.StockBroker.broker.service.entity.UserStock;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class BrokerService {

    private final WebClient webClient =  WebClient.builder().build();



    public boolean buy(UserDto dto)
    {
        String ticker  =  dto.getTicker();
        float currentPrice  = webClient.get().uri("http://localhost:8081/stock/"+ticker).retrieve().bodyToMono(Float.class).block();
        User user =  webClient.get().uri("http://localhost:8080/users/"+ dto.getUserId()).retrieve().bodyToMono(User.class).block();
        ServiceDto buyDto =  new ServiceDto(dto.getUserId(), dto.getTicker() , currentPrice ,dto.getQuantity() , StockOption.BUY );
        webClient.post().uri("http://localhost:8080/users/update").bodyValue(buyDto).retrieve().bodyToMono(Boolean.class).block();
        String subject =  "Purchase of new Stock ";
        String text  =  "Stock" + dto.getTicker() + "is  successfully purchased \n ,  Total bill "+dto.getQuantity()*currentPrice + "\n remaining balance "+user.getWalletBalance() ;
        sendEmail(user.getEmail() , subject , text);
        return true;
    }

    public boolean sell( UserDto  dto )
    {
        String ticker  =  dto.getTicker();
        float currentPrice  = webClient.get().uri("http://localhost:8081/stock/"+ticker).retrieve().bodyToMono(Float.class).block();
        User user =  webClient.get().uri("http://localhost:8080/users/"+ dto.getUserId()).retrieve().bodyToMono(User.class).block();

        ServiceDto sellDto = new ServiceDto(dto.getUserId() ,dto.getTicker() , currentPrice, dto.getQuantity() , StockOption.SELL  );
        webClient.post().uri("http://localhost:8080/users/update").bodyValue(sellDto).retrieve().bodyToMono(Boolean.class).block();
        String subject =  " Stock  sold ";
        String text  =  "Stock" + dto.getTicker() + "is  successfully sold \n ,  Total amount to be credited "+dto.getQuantity()*currentPrice + "\n Total balance "+user.getWalletBalance() ;
        sendEmail(user.getEmail() , subject , text);

        return true;
    }


    private void  sendEmail(String reciver  , String subject , String text)
    {
        webClient.get()
                .uri("http://localhost:8082/send?reciver={receiver}&subject={subject}&text={text}",
                        reciver, subject, text)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }


}
