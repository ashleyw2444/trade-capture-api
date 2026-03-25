package com.example.tradecapture.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.tradecapture.entity.*;

@Repository
public interface PositionRepository extends JpaRepository<Position, PositionId> {
    
    // get a specific Position by positionId
    Optional<Position> findByPositionId(PositionId positionId);

    // get instruments by asset class
    List<Position> findByAssetClass(String assetClass);

    // get instruments by currency
    List<Position> findByCurrency(String currency);

    // get instruments by acountId
    List<Position> findByIdAccountId(Long accountId);

    // get instruments by instrumentId
    List<Position> findByIdInstrumentId(Long instrumentId);
    
}