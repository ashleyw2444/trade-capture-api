package com.example.tradecapture.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Enumerated(EnumType.STRING)
    private Status status; 

    private String accountName;
    private LocalDateTime createdAt;

    public Account(Status status, String accountName, 
        LocalDateTime createdAt){
        this.status = status;
        this.accountName = accountName;
        this.createdAt = createdAt;
    }

    public Long getAccountId(){
        return this.accountId;
    }

    public Status getStatus(){
        return this.status;
    }

    public String getAccountName(){
        return this.accountName;
    }

    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }

    public void setStatus(Status status){
        this.status = status;
    }

    public void setAccountName(String accountName){
        this.accountName = accountName;
    }
    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }
}