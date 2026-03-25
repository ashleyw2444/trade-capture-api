package com.example.tradecapture.repository;

import com.example.tradecapture.entity.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {
    
    // get trade by specific tradeId
    Optional<Trade> findByTradeId(Long tradeId);

    // get trade by specific externalId
    Optional<Trade> findByExternalId(String externalId);

    // get trades created after certain time
    List<Trade> findByCreatedAtAfter(LocalDateTime t);
    
    // get trades executed after certain time
    List<Trade> findByExecutedAtAfter(LocalDateTime t);

    // get trades created before certain time
    List<Trade> findByCreatedAtBefore(LocalDateTime t);
    
    // get trades executed before certain time
    List<Trade> findByExecutedAtBefore(LocalDateTime t);

    // get trades created between certain times
    List<Trade> findByCreatedAtBetween(LocalDateTime t1, LocalDateTime t2);
    
    // get trades executed between certain times
    List<Trade> findByExecutedAtBetween(LocalDateTime t1, LocalDateTime t2);

    // get trades for a specific account
    List<Trade> findByAccount(Account a);

    // get trades for a specific instrument
    List<Trade> findByInstrument(Instrument i);

    // get trades for side
    List<Trade> findBySide(Side side);

    // get N trades where id > specified id ordered
    List<Trade> findByIdGreaterThanOrderByIdAsc(Long cursor, Pageable pageable);
}