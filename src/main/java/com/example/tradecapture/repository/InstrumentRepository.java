package com.example.tradecapture.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tradecapture.entity.*;

@Repository
public interface InstrumentRepository extends JpaRepository<Instrument, Long> {

    // get a specific instrument by id
    Optional<Instrument> findByInstrumentId(Long instrumentId);

    // get instruments by asset class
    List<Instrument> findByAssetClass(String assetClass);

    // get instruments by currency
    List<Instrument> findByCurrency(String currency);
}