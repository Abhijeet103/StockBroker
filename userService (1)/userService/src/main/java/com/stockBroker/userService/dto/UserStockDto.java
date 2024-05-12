package com.stockBroker.userService.dto;


import com.stockBroker.userService.entity.StockOption;
import lombok.Data;

@Data
public class UserStockDto {

    String UserId ;
    String ticker ;
    float purchasePrice ;
    int quantity ;
    StockOption stockOption ;
}
