package com.example.tradecapture.exception;

public class TradeNotFoundException extends RuntimeException {
    
    public TradeNotFoundException(Long id) {
        super("Trade does not exist: " + id);
    }

}
