package com.stockBroker.userService.entity;


import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id ;
    @JsonIgnore
    String userName;
    @JsonIgnore
    String Phone ;
    String email ;
    float walletBalance  ;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    UserDetail  userDetail ;
    @OneToMany(cascade = CascadeType.ALL)
    List<UserStock> stocks;
}
