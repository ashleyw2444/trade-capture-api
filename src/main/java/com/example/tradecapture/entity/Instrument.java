package com.example.tradecapture.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "instruments")
public class Instrument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long instrumentId;

    private String assetClass;
    private String currency;

    public Instrument(String assetClass, String currency){
        this.assetClass = assetClass;
        this.currency = currency;
    }

    public Long getInstrumentId(){
        return this.instrumentId;
    }

    public String getAssetClass(){
        return this.assetClass;
    }

    public String getCurrency(){
        return this.currency;
    }

    public void setAssetClass(String assetClass){
        this.assetClass = assetClass;
    }

    public void setCurrency(String currency){
        this.currency = currency;
    }
}