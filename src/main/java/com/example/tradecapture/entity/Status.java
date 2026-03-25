package com.example.tradecapture.entity;

public enum Status {
    ACTIVE(1),
    SUSPENDED(2);

    private int value;

    Status(int value){
        this.value = value;
    }

    public String getValue(){
        return (value == 1) ? "ACTIVE": "SUSPENDED";
    }
}