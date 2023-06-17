package com.Joysbrightt.ussdservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class UssdIndexController {
    @PostMapping(path = "ussd")
    public String ussdIngress(@RequestParam String sessionId, @RequestParam String serviceCode,@RequestParam String phoneNumber, @RequestParam String text) throws IOException{
        try{
            return ussdRoutingService.menulevelRouter(sessionId, serviceCode, phoneNumber, text);
        }catch (IOException exception){
            return "END Service is temporarily down";
        }
    }
}
