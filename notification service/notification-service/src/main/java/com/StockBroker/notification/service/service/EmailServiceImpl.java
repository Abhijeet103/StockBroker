package com.StockBroker.notification.service.service;


import com.StockBroker.notification.service.service.EmailService;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {

    public boolean sendMail(String reciver , String subject , String text)
    {
        boolean flag = false ;
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        String userName  = "devtestapi209";
        // gmail APP password
        String password = "ichxgmhygwgeafsp";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });

        try {
            Message message  =  new MimeMessage(session) ;

            message.setRecipient(Message.RecipientType.TO  , new InternetAddress(reciver));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
            flag =  true ;
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error sending email");
        }

        return flag ;


    }
}
