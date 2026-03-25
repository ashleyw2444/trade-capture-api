package com.example.tradecapture.entity;

public enum Side {
    BUY(1),
    SELL(2);

    private int value;

    Side(int value){
        this.value = value;
    }

    public String getValue(){
        return (value == 1) ? "BUY": "SELL";
    }
}