package com.example.tradecapture.service;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

@Service
public class PricingService {

    public BigDecimal getPrice(String symbol) throws Exception{
        Random random = new Random();
        double time = random.nextDouble();
        double price = random.nextDouble() * 2 + 10;
        if(time < 0.7){
            return BigDecimal.valueOf(price);
        } else if(time < 0.9){
            int delay = random.nextInt(3) + 2;
            TimeUnit.SECONDS.sleep(delay);
            return BigDecimal.valueOf(price);
        } else {
            throw new Exception("timeout");
        }
    }
}