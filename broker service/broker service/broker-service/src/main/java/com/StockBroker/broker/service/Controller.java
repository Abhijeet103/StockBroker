package com.StockBroker.broker.service;


import com.StockBroker.broker.service.dto.UserDto;
import com.StockBroker.broker.service.service.BrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {


    @Autowired
    BrokerService service ;
    @PostMapping("/broker/buy")
    boolean buy(@RequestBody UserDto dto)
    {
        return  service.buy(dto);
    }


    @PostMapping("/broker/sell")
    boolean sell(@RequestBody  UserDto  dto)
    {
        return  service.sell(dto);
    }

}
