package io.github.vitor0x5.domains.account.services;

import io.github.vitor0x5.domains.account.dtos.AccountResponseDTO;
import io.github.vitor0x5.domains.account.dtos.CreateAccountDTO;
import io.github.vitor0x5.domains.account.entities.Account;
import io.github.vitor0x5.domains.account.repositories.AccountsRepository;
import io.github.vitor0x5.domains.user.repositories.UsersRepository;
import io.github.vitor0x5.shared.exceptions.types.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateAccountService {
    private final AccountsRepository accountsRepository;
    private final UsersRepository usersRepository;
    private final ModelMapper mapper;

    public CreateAccountService(AccountsRepository accountsRepository, UsersRepository usersRepository, ModelMapper mapper) {
        this.accountsRepository = accountsRepository;
        this.usersRepository = usersRepository;
        this.mapper = mapper;
    }

    @Transactional
    public AccountResponseDTO execute(String userEmail, CreateAccountDTO accountDTO) {
        var user = usersRepository.findByEmail(userEmail)
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER));

        Account newAccount = mapper.map(accountDTO, Account.class);
        newAccount.setUser(user);

        accountsRepository.save(newAccount);

        return mapper.map(newAccount, AccountResponseDTO.class);
    }
}
