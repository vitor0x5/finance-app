package io.github.vitor0x5.domains.transaction.repositories;

import io.github.vitor0x5.domains.transaction.entities.Transaction;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionsRepository {
    Transaction save(Transaction income);
    Optional<List<Transaction>> findByUserId(UUID userId, Pageable pageable);
    Optional<Transaction> findOneById(UUID userId);
    Optional<Transaction> findById(UUID transactionId);
    void deleteById(UUID id);
}
