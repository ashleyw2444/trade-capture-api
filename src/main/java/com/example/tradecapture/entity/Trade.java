package com.example.tradecapture.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "trades")
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tradeId;

    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name="instrument_id")
    private Instrument instrument;

    @Enumerated(EnumType.STRING)
    private Side side; 

    private Integer quantity;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private PricingStatus pricingStatus;

    private LocalDateTime executedAt;
    private LocalDateTime createdAt;

    @Column(unique=true)
    private String externalId;

    public Trade() {}
    
    public Trade(Account account, Instrument instrument, 
        Side side, Integer quantity, BigDecimal price, 
        LocalDateTime executedAt){
        this.account = account;
        this.instrument = instrument;
        this.side = side;
        this.quantity = quantity;
        this.price = price;
        this.createdAt = LocalDateTime.now();
        this.executedAt = executedAt;
        this.pricingStatus = (price != null) ? PricingStatus.COMPLETED : PricingStatus.PENDING;
    }
    
    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }

    public void setPrice(BigDecimal price){
        this.price = price;
    }

    public void setPricingStatus(PricingStatus pricingStatus){
        this.pricingStatus = pricingStatus;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }

    public void setExecutedAt(LocalDateTime executedAt){
        this.executedAt = executedAt;
    }

    public Long getTradeId(){
        return this.tradeId;
    }

    public Account getAccount(){
        return this.account;
    }

    public Instrument getInstrument(){
        return this.instrument;
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

    public PricingStatus getPricingStatus(){
        return this.pricingStatus;
    }

    public LocalDateTime getExecutedAt(){
        return this.executedAt;
    }

    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }

    public String getExternalId(){
        return this.externalId;
    }
}