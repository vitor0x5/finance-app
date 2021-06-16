package io.github.vitor0x5.domains.account.repositories.implementations;

import io.github.vitor0x5.domains.account.entities.Account;
import io.github.vitor0x5.domains.account.repositories.AccountsRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountsRepositoryImplementation extends AccountsRepository, JpaRepository<Account, UUID> {
}
