package com.stockBroker.userService.service;


import com.stockBroker.userService.dto.UserStockDto;
import com.stockBroker.userService.entity.StockOption;
import com.stockBroker.userService.entity.User;
import com.stockBroker.userService.entity.UserStock;
import com.stockBroker.userService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository ;

    @Autowired
    StockService  stockService ;
   public  List<User> getAllUser()
    {
        return  userRepository.findAll();
    }

   public  User getUser(String id)
    {
        System.out.println(id);
        return  userRepository.findById(id).orElseThrow();
    }

   public  User createUser(User user)
    {
        userRepository.save(user);
        return user ;
    }

   public User updateUser(User user)
    {
        userRepository.save(user);
        return user;
    }

  public   User deleteUser(String id )
    {
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
        return user;
    }

    public User addBalance(String userId , String balance)
    {
        User user = userRepository.findById(userId).orElseThrow();
        user.setWalletBalance(user.getWalletBalance() + Integer.parseInt(balance)) ;
        updateUser(user);
        return user ;
    }
    public boolean UpdateUserStock(UserStockDto userStockDto)
    {
        if(userStockDto.getStockOption().equals(StockOption.BUY))
        {
            UserStock stock =  getStockFromUserId(userStockDto);
            User user =  userRepository.findById(userStockDto.getUserId()).orElseThrow();
            List<UserStock> userStocks = user.getStocks();
            float cost = userStockDto.getPurchasePrice()*userStockDto.getQuantity();
            if(cost > user.getWalletBalance())
            {
                throw new RuntimeException("insuffcient balance");
            }
            if(stock  == null )
            {
                stock  = stockService.createStock(new UserStock(userStockDto.getTicker() , userStockDto.getPurchasePrice() , userStockDto.getQuantity()));
                userStocks.add(stock);
            }else {
                stock  = stockService.UpdateStock(stock.getId() ,  userStockDto.getQuantity() , userStockDto.getPurchasePrice());
            }
            user.setWalletBalance(user.getWalletBalance() - cost);
            updateUser(user);

        }
        if(userStockDto.getStockOption().equals(StockOption.SELL))
        {
            UserStock stock =  getStockFromUserId(userStockDto);
            if(stock ==  null)
            {
                throw new RuntimeException("you don't have it in the portfolio");
            }
            else {
                User user =  userRepository.findById(userStockDto.getUserId()).orElseThrow();
                stockService.sellStock(stock.getId() , userStockDto.getQuantity() , userStockDto.getPurchasePrice());
                float cost  =  userStockDto.getQuantity() + userStockDto.getPurchasePrice();
                user.setWalletBalance(user.getWalletBalance() + cost);
                updateUser(user);
            }
        }


    return true;

    }

    UserStock getStockFromUserId(UserStockDto userStockDto)
    {
        User  user  =  userRepository.findById(userStockDto.getUserId()).orElseThrow();
        List<UserStock> stocks  =  user.getStocks();
        for(UserStock stock :  stocks)
        {
            if(stock.getTicker().equals(userStockDto.getTicker()))
            {
                return stock ;
            }
        }

        return null ;

    }

}
