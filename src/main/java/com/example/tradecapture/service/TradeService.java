package com.example.tradecapture.service;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.example.tradecapture.dto.TradeRequestDTO;
import com.example.tradecapture.dto.TradeResponseDTO;
import com.example.tradecapture.entity.*;
import com.example.tradecapture.exception.TradeNotFoundException;
import com.example.tradecapture.repository.AccountRepository;
import com.example.tradecapture.repository.InstrumentRepository;
import com.example.tradecapture.repository.TradeRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import javax.naming.directory.InvalidAttributesException;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Service
public class TradeService {

    private final TradeRepository tradeRepository;
    private final AccountRepository accountRepository;
    private final InstrumentRepository instrumentRepository;
    private final PricingService pricingService;

    public TradeService(TradeRepository tradeRepository, 
        AccountRepository accountRepository,
        InstrumentRepository instrumentRepository,
        PricingService pricingService){
        this.tradeRepository = tradeRepository;
        this.accountRepository = accountRepository;
        this.instrumentRepository = instrumentRepository;
        this.pricingService = pricingService;
    }

    public Trade toEntity(TradeRequestDTO dto){
        Optional<Account> account = this.accountRepository.findByAccountId(dto.getAccountId());
        account.orElseThrow();
        Optional<Instrument> instrument = this.instrumentRepository.findByInstrumentId(dto.getInstrumentId());
        instrument.orElseThrow();
        return new Trade(account.get(), instrument.get(), 
            dto.getSide(), dto.getQuantity(), dto.getPrice(), null);
    }

    public TradeResponseDTO toDTO(Trade trade){
        return new TradeResponseDTO(trade.getPricingStatus(), 
        trade.getExecutedAt(), trade.getCreatedAt());
    }

    @Transactional
    public Trade createTrade(Trade trade) {
        if(trade == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Trade is invalid");
        }
        if(trade.getQuantity() < 0
            || trade.getSide() == null || trade.getPrice().compareTo(BigDecimal.ZERO) <= 0
            || (trade.getSide() != Side.BUY && trade.getSide() != Side.SELL)
            || trade.getAccount() == null || trade.getInstrument() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Trade is invalid");
        }
        if(trade.getCreatedAt() != null){
            if(trade.getCreatedAt().isAfter(LocalDateTime.now())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Trade is invalid");
            }
            if(trade.getCreatedAt() != null && (trade.getExecutedAt().isAfter(LocalDateTime.now())
            || trade.getCreatedAt().isAfter(trade.getExecutedAt()))){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Trade is invalid");
            }
        } else {
            trade.setCreatedAt(LocalDateTime.now());
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
        CompletableFuture.runAsync(() -> {
            // try and retry once 
            int maxTryCt = 2;
            for(int tryCt = 0; tryCt < maxTryCt; tryCt++){
                try {
                    Optional<Trade> foundTrade = this.tradeRepository.findByTradeId(trade.getTradeId());
                    if(foundTrade.isPresent()){
                        Trade t = foundTrade.get();
                        t.setPrice(this.pricingService.getPrice("AAPL"));
                        t.setPricingStatus(PricingStatus.COMPLETED);
                        this.tradeRepository.save(t);
                        break;
                    }
                } catch (Exception e){
                    System.out.println("exception: " + e);
                }
            }
        }).orTimeout(4, TimeUnit.SECONDS);
        return this.tradeRepository.save(trade);
    }

    public Page<Trade> getTrades(int limit, Long cursor) {
        Pageable pageable = PageRequest.of(0, limit);
        if(cursor == null){
            cursor = 0L;
        } 
        return this.tradeRepository.findByTradeIdGreaterThan(cursor, pageable);
    }

    public Trade getTrade(Long tradeId){
        return this.tradeRepository.findByTradeId(tradeId).orElseThrow(() -> new TradeNotFoundException(tradeId));
    }
}