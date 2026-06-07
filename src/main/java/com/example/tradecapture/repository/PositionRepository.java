package com.example.tradecapture.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.tradecapture.entity.*;

@Repository
public interface PositionRepository extends JpaRepository<Position, PositionId> {
    
    // get a specific Position by positionId
    Optional<Position> findById(PositionId positionId);

    // get positions by acountId
    List<Position> findByIdAccountId(Long accountId);

    // get positions by instrumentId
    List<Position> findByIdInstrumentId(Long instrumentId);
    
}