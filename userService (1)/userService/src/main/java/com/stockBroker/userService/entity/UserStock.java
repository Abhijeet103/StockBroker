package com.stockBroker.userService.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class UserStock {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String ticker;
    float  avgBuyPrice;
    int  quantity ;

    public UserStock(String ticker, float avgBuyPrice, int quantity)
    {
        this.ticker = ticker ;
        this.avgBuyPrice =  avgBuyPrice ;
        this.quantity =  quantity;
    }
}
