package com.example.tradecapture.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trades")
public class TradeController {

    // Inject TradeService

    @PostMapping
    public void createTrade() {
        // TODO: implement POST endpoint
    }

    @GetMapping
    public void getTrades() {
        // TODO: implement GET endpoint with pagination
    }
}