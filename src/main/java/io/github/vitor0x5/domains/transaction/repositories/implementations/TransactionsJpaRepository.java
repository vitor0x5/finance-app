package io.github.vitor0x5.domains.transaction.repositories.implementations;

import io.github.vitor0x5.domains.transaction.entities.Transaction;
import io.github.vitor0x5.domains.transaction.repositories.TransactionsRepository;
import io.github.vitor0x5.domains.user.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionsJpaRepository extends TransactionsRepository, JpaRepository<Transaction, UUID> {
    List<Transaction> findByUserId(UUID userid);
    void deleteById(UUID id);
}
