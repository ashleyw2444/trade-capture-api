package com.example.tradecapture.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trade_events")
public class TradeEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long tradeEventId;

    @ManyToOne
    @JoinColumn(name="trade_id")
    private Long tradeId;

    @Enumerated(EnumType.STRING)
    private EventType eventType; 

    private String payload;
    private LocalDateTime createdAt;

    public TradeEvent(Long tradeId, EventType eventType, 
        String payload, LocalDateTime createdAt){
        this.tradeId = tradeId;
        this.eventType = eventType;
        this.payload = payload;
        this.createdAt = createdAt;
    }

    public Long getTradeEventId(){
        return this.tradeEventId;
    }

    public Long getTradeId(){
        return this.tradeId;
    }

    public EventType getEventType(){
        return this.eventType;
    }

    public String getPayload(){
        return this.payload;
    }

    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }

    public void setTradeId(Long tradeId){
        this.tradeId = tradeId;
    }

    public void setEventType(EventType eventType){
        this.eventType = eventType;
    }

    public void setPayload(String payload){
        this.payload = payload;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }
}