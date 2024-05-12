package com.stockBroker.userService.repository;

import com.stockBroker.userService.entity.UserStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<UserStock , String> {

    UserStock findByTicker(String ticker) ;
}
