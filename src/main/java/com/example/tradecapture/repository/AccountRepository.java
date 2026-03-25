package com.example.tradecapture.repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.tradecapture.entity.*;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    // get a specific account by id
    Optional<Account> findByAccountId(Long accountId);

    // get accounts by status
    List<Account> findByStatus(Status status);

    // get a specific account by account name
    List<Account> findByAccountNameLike(String name);

    // get accounts created before a specific time
    List<Account> findByCreatedAtBefore(LocalDateTime t);

    // get accounts created after a specific time
    List<Account> findByCreatedAtAfter(LocalDateTime t);

    // get accounts between specific times
    List<Account> findByCreatedAtBetween(LocalDateTime t1, LocalDateTime t2);
}