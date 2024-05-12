package com.StockBroker.broker.service.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class UserStock {

   private String id ;
  private  String ticker;
    private float avgBuyPrice ;
   private  int quantity ;

}
