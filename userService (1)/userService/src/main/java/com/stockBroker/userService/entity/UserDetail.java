package com.stockBroker.userService.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String panCardNo;
    String aaddharNo ;
    String accountNo;
}
