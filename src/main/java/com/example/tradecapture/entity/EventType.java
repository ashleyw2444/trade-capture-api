package com.example.tradecapture.entity;

public enum EventType {
    CREATED(1),
    UPDATED(2),
    CANCELLED(3);

    private int value;

    EventType(int value){
        this.value = value;
    }

    public String getValue(){
        return (value == 1) ? "CREATED": 
        (value == 2) ? "UPDATED": "CANCELLED";
    }
}