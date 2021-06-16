package io.github.vitor0x5.domains.account.repositories;

import io.github.vitor0x5.domains.account.entities.Account;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountsRepository {
    Account save(Account acc);
    Optional<Account> findById(UUID id);
    Optional<List<Account>> findByUserId(UUID userId, Pageable pageable);
}
