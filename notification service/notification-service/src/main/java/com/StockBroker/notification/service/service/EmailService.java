package com.StockBroker.notification.service.service;

public interface EmailService {

    boolean sendMail(String reciver , String subject , String text) ;
}
