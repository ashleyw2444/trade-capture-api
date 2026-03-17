package com.example.tradecapture.repository;

import com.example.tradecapture.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {
    // Add custom queries here when ready
}