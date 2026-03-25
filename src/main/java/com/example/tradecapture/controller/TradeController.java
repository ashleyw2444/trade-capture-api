package com.example.tradecapture.controller;
import com.example.tradecapture.entity.*;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.tradecapture.service.TradeService;

@RestController
@RequestMapping("/trades")
public class TradeController {

    private final TradeService tradeService;

    public TradeController(TradeService tradeService){
        this.tradeService = tradeService;
    }

    @PostMapping
    public Trade createTrade(@RequestBody Trade trade) {
        try {
            return tradeService.createTrade(trade);
        } catch (Exception e){
            System.out.println("Exception occurred: " + e);
            return null;
        }
    }

    @GetMapping
    public List<Trade> getTrades(@RequestParam int limit, 
        @RequestParam Long cursor) {
        return tradeService.getTrades(limit, cursor);
    }
}