package com.example.tradecapture.controller;
import com.example.tradecapture.dto.TradeRequestDTO;
import com.example.tradecapture.dto.TradeResponseDTO;
import com.example.tradecapture.entity.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.example.tradecapture.service.TradeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/trades")
public class TradeController {

    private final TradeService tradeService;

    public TradeController(TradeService tradeService){
        this.tradeService = tradeService;
    }

    
    public TradeResponseDTO createTrade(@Valid @RequestBody TradeRequestDTO request){
        return this.tradeService.toDTO(this.tradeService.toEntity(request));
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
    public Page<Trade> getTrades(@RequestParam int limit, 
        @RequestParam Long cursor) {
        return tradeService.getTrades(limit, cursor);
    }

    @GetMapping("/{id}")
    public Trade getTrade(@PathVariable Long id) {
        return tradeService.getTrade(id);
    }
}