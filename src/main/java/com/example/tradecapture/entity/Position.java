package com.example.tradecapture.entity;
import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "positions")
public class Position {
    @EmbeddedId
    private PositionId id;

    private Integer netQuantity;
    private BigDecimal avgPrice;

    public Position(PositionId id,
        Integer netQuantity, BigDecimal avgPrice){
        this.id = id;
        this.netQuantity = netQuantity;
        this.avgPrice = avgPrice;
    }

    public PositionId getPositionId(){
        return this.id;
    }

    public Integer getNetQuantity(){
        return this.netQuantity;
    }

    public BigDecimal getAvgPrice(){
        return this.avgPrice;
    }

    public void setNetQuantity(Integer netQuantity){
        this.netQuantity = netQuantity;
    }

    public void setAvgPrice(BigDecimal avgPrice){
        this.avgPrice = avgPrice;
    }
}