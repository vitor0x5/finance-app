package io.github.vitor0x5.domains.account.services;

import io.github.vitor0x5.domains.account.dtos.AccountResponseDTO;
import io.github.vitor0x5.domains.account.entities.Account;
import io.github.vitor0x5.domains.account.repositories.AccountsRepository;
import io.github.vitor0x5.domains.user.repositories.UsersRepository;
import io.github.vitor0x5.shared.exceptions.types.BusinessException;
import io.github.vitor0x5.shared.exceptions.types.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetAccountService {
    private final AccountsRepository accountsRepository;
    private final UsersRepository usersRepository;
    private final ModelMapper mapper;

    public GetAccountService(AccountsRepository accountsRepository, UsersRepository usersRepository, ModelMapper mapper) {
        this.accountsRepository = accountsRepository;
        this.usersRepository = usersRepository;
        this.mapper = mapper;
    }

    public AccountResponseDTO execute(String userEmail, UUID accountId) {
        Account account = accountsRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.ACCOUNT));

        if(!account.getUser().getEmail().equals(userEmail)) {
            throw new BusinessException(BusinessException.INCORRECT_USER);
        }

        return mapper.map(account, AccountResponseDTO.class);
    }
}
