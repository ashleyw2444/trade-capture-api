package com.example.tradecapture.service;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.example.tradecapture.entity.*;
import com.example.tradecapture.repository.AccountRepository;
import com.example.tradecapture.repository.InstrumentRepository;
import com.example.tradecapture.repository.TradeRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import javax.naming.directory.InvalidAttributesException;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Service
public class TradeService {

    private final TradeRepository tradeRepository;
    private final AccountRepository accountRepository;
    private final InstrumentRepository instrumentRepository;

    public TradeService(TradeRepository tradeRepository, 
        AccountRepository accountRepository,
        InstrumentRepository instrumentRepository){
        this.tradeRepository = tradeRepository;
        this.accountRepository = accountRepository;
        this.instrumentRepository = instrumentRepository;
    }

    @Transactional
    public Trade createTrade(Trade trade) {
        if(trade == null || trade.getQuantity() < 0
            || trade.getSide() == null || trade.getPrice().compareTo(BigDecimal.ZERO) <= 0
            || trade.getExecutedAt().isAfter(LocalDateTime.now())
            || trade.getCreatedAt().isAfter(LocalDateTime.now())
            || trade.getCreatedAt().isAfter(trade.getExecutedAt())
            || (trade.getSide() != Side.BUY && trade.getSide() != Side.SELL)
            || trade.getAccount() == null || trade.getInstrument() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Trade is invalid");
        }
        // make sure account & instrument exist
        Optional<Account> possibleAccount = this.accountRepository.findByAccountId(trade.getAccount().getAccountId());
        possibleAccount.orElseThrow();
        Optional<Instrument> possibleInstrument = this.instrumentRepository.findByInstrumentId(trade.getInstrument().getInstrumentId());
        possibleInstrument.orElseThrow();
        // make sure no duplicate trade 
        Optional<Trade> potentialDupTrade = this.tradeRepository.findByExternalId(trade.getExternalId());
        if(potentialDupTrade.isPresent()){
            return potentialDupTrade.get();
        }
        return tradeRepository.save(trade);
    }

    public List<Trade> getTrades(int limit, Long cursor) {
        Pageable pageable = PageRequest.of(0, limit);
        if(cursor == null){
            cursor = 0L;
        } 
        return this.tradeRepository.findByIdGreaterThanOrderByIdAsc(cursor, pageable);
    }
}