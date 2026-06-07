package com.example.tradecapture.dto;

import java.time.LocalDateTime;

import com.example.tradecapture.entity.PricingStatus;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import jakarta.persistence.EnumType;

public class TradeResponseDTO {
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private PricingStatus pricingStatus;

    private LocalDateTime executedAt;

    @Past
    private LocalDateTime createdAt;

    public TradeResponseDTO(){}

    public TradeResponseDTO(PricingStatus pricingStatus, 
        LocalDateTime executedAt, LocalDateTime createdAt){
        this.pricingStatus = pricingStatus;
        this.executedAt = executedAt;
        this.createdAt = createdAt;
    }
 
    public PricingStatus getPricingStatus(){
        return this.pricingStatus;
    }
    
    public LocalDateTime getExecutedAt(){
        return this.executedAt;
    }

    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }
}
