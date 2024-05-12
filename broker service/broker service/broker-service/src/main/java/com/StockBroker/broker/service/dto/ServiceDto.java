package com.StockBroker.broker.service.dto;


import com.StockBroker.broker.service.entity.StockOption;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServiceDto {

    String userId;
    String ticker ;
    float purchasePrice ;
    int quantity ;
    StockOption stockOption ;
}
