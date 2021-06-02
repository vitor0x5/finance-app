package io.github.vitor0x5.domains.transaction.repositories.implementations;

import io.github.vitor0x5.domains.transaction.entities.Transaction;
import io.github.vitor0x5.domains.transaction.repositories.TransactionsRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionsJpaRepository extends TransactionsRepository, JpaRepository<Transaction, UUID> {
    Optional<List<Transaction>> findByUserId(UUID userid, Pageable pageable);
    void deleteById(UUID id);
}
