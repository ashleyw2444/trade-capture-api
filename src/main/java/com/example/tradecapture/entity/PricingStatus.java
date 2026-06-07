package com.example.tradecapture.entity;

public enum PricingStatus {
    PENDING(0),
    COMPLETED(1);

    private int value;

    PricingStatus(int value){
        this.value = value;
    }

    public String getValue(){
        return (value == 0) ? "PENDING": "COMPLETED";
    }
}