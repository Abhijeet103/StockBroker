package com.stockBroker.userService.service;

import com.stockBroker.userService.entity.User;
import com.stockBroker.userService.entity.UserStock;
import com.stockBroker.userService.repository.StockRepository;
import com.stockBroker.userService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StockService {

    @Autowired
    StockRepository  stockRepository ;


    UserStock UpdateStock(String stockId , int purchaseQuantity , float purchasePrice)
    {
        UserStock stock   = stockRepository.findById(stockId).orElseThrow();
        float totalPrice =  stock.getQuantity()*stock.getAvgBuyPrice() + purchasePrice*purchaseQuantity ;
        int totalQuantity = stock.getQuantity() + purchaseQuantity ;
        float avgPriceNew =  totalPrice/totalQuantity ;
        stock.setAvgBuyPrice(avgPriceNew);
        stock.setQuantity(totalQuantity);
        return stock ;
    }

    UserStock sellStock(String stockId , int quantity , float price)
    {
        UserStock stock   = stockRepository.findById(stockId).orElseThrow();
        int rem = stock.getQuantity() - quantity ;
        if(rem <0)
        {
            throw new RuntimeException("Selling too much ");
        }
        stock.setQuantity(rem);

        return  stock ;

    }
    UserStock createStock(UserStock userStock)
    {
        return  stockRepository.save(userStock);
    }


}
