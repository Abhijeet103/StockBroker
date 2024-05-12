package com.StockBroker.broker.service.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class User {
   private String id ;
   private String email ;
    private  float walletBalance ;
   private  List<UserStock> stocks ;
}
