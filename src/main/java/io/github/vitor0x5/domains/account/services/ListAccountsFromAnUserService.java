package io.github.vitor0x5.domains.account.services;

import io.github.vitor0x5.domains.account.dtos.AccountResponseDTO;
import io.github.vitor0x5.domains.account.entities.Account;
import io.github.vitor0x5.domains.account.repositories.AccountsRepository;
import io.github.vitor0x5.shared.exceptions.types.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ListAccountsFromAnUserService {
    private final AccountsRepository accountsRepository;
    private final ModelMapper mapper;

    public ListAccountsFromAnUserService(AccountsRepository accountsRepository, ModelMapper mapper) {
        this.accountsRepository = accountsRepository;
        this.mapper = mapper;
    }

    public List<AccountResponseDTO> execute(UUID userId, int page, int elements) {
        Pageable pageable = PageRequest.of(page, elements);

        List<Account> accounts = accountsRepository.findByUserId(userId, pageable)
                .orElseThrow(() -> new NotFoundException(NotFoundException.ACCOUNT));

        return buildResponseData(accounts);
    }

    private List<AccountResponseDTO> buildResponseData(List<Account> accounts) {
        List<AccountResponseDTO> response = new ArrayList<>();
        accounts.forEach(a -> {
            response.add(mapper.map(a, AccountResponseDTO.class));
        });

        return response;
    }
}
