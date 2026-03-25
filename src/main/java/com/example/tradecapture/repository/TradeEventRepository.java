package com.example.tradecapture.repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.tradecapture.entity.*;

@Repository
public interface TradeEventRepository extends JpaRepository<TradeEvent, Long> {
    
    // get trade event by id
    Optional<TradeEvent> findByTradeEventId(Long tradeEventId);

    // get trade events by tradeid
    List<TradeEvent> findByTradeId(Long tradeId);

    // get trade events by eventType
    List<TradeEvent> findByEventType(EventType eventType);
    
    // get trade events created before a specific time
    List<TradeEvent> findByCreatedAtBefore(LocalDateTime t);

    // get trade events created after a specific time
    List<TradeEvent> findByCreatedAtAfter(LocalDateTime t);

    // get trade events between specific times
    List<TradeEvent> findByCreatedAtBetween(LocalDateTime t1, LocalDateTime t2);
}