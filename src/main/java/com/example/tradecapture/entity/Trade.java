package com.example.tradecapture.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

enum Side {
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
    private LocalDateTime executedAt;
    private LocalDateTime createdAt;

    public Trade(){

    }
    
    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }

    public void setPrice(BigDecimal price){
        this.price = price;
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

    public LocalDateTime getExecutedAt(){
        return this.executedAt;
    }

    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }

}