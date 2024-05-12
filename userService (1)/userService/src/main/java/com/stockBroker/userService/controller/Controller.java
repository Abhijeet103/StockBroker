package com.stockBroker.userService.controller;


import com.stockBroker.userService.dto.UserStockDto;
import com.stockBroker.userService.entity.User;
import com.stockBroker.userService.entity.UserStock;
import com.stockBroker.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    UserService userService ;

    @GetMapping("/users")
    List<User> getAllUser()
    {
        return  userService.getAllUser();
    }

    @GetMapping("/users/{userId}")
    User getUser(@PathVariable String userId)
    {
        System.out.println(userId);
        return userService.getUser(userId);
    }
    @GetMapping("/users/addBalance/{userId}/{amount}")
    User addMoney(@PathVariable String  userId , @PathVariable String amount)
    {
        return userService.addBalance(userId , amount);
    }
    @PostMapping("/users")
    User  createUser(@RequestBody  User user)
    {
        return userService.createUser(user);

    }

    @DeleteMapping("/users/{userId}")
    User deleteUser(@PathVariable  String userId)
    {
        return userService.deleteUser(userId);
    }

    @PostMapping("/users/update")
    boolean updateUserStock(@RequestBody UserStockDto  userStockDto)
    {
        System.out.println(userStockDto);
        return  userService.UpdateUserStock(userStockDto);
    }
}
