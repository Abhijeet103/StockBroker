package com.StockBroker.notification.service;


import com.StockBroker.notification.service.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    EmailService emailService ;
    @GetMapping("/send")
    boolean sendEmail(@RequestParam  String reciver , @RequestParam String subject , @RequestParam String text )
    {
        try
        {
            return emailService.sendMail(reciver , subject , text);

        }
        catch (Exception e)
        {
            throw new RuntimeException("error in sending email ");
        }
    }
}
