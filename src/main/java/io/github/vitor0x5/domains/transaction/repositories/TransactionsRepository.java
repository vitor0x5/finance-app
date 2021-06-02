package io.github.vitor0x5.domains.transaction.repositories;

import io.github.vitor0x5.domains.transaction.entities.Transaction;
import io.github.vitor0x5.domains.user.entities.AppUser;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionsRepository {
    Transaction save(Transaction income);
    List<Transaction> findByUserId(UUID userId);
    Optional<Transaction> findById(UUID transactionId);
    void deleteById(UUID id);
}
