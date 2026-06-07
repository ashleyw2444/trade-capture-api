package com.example.tradecapture.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.tradecapture.entity.*;
import com.example.tradecapture.repository.AccountRepository;
import com.example.tradecapture.repository.InstrumentRepository;
import com.example.tradecapture.repository.TradeRepository;
import com.example.tradecapture.service.PricingService;

import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public class TradeRequestDTO {
    @NotNull
    private Long accountId;

    @NotNull
    private Long instrumentId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Side side; 

    @Positive
    private Integer quantity;

    @Positive
    private BigDecimal price;

    @NotNull
    private String externalId;

    public Long getAccountId(){
        return this.accountId;
    }

    public Long getInstrumentId(){
        return this.instrumentId;
    }

    public Side getSide(){
        return this.side;
    }

    public Integer getQuantity(){
        return this.quantity;
    }

    public BigDecimal getPrice(){
        return this.price;
    }

    public String getExternalId(){
        return this.externalId;
    }
}
