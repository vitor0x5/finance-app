package io.github.vitor0x5.domains.transaction.services;

import io.github.vitor0x5.domains.transaction.TransactionTypes;
import io.github.vitor0x5.domains.transaction.dtos.BalanceDTO;
import io.github.vitor0x5.domains.transaction.dtos.TransactionsBalanceResponseDTO;
import io.github.vitor0x5.domains.transaction.dtos.TransactionResponseDataDTO;
import io.github.vitor0x5.domains.transaction.entities.Transaction;
import io.github.vitor0x5.domains.transaction.repositories.TransactionsRepository;
import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.repositories.UsersRepository;
import io.github.vitor0x5.shared.errors.types.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetTransactionsFromAnUserService {
    private final UsersRepository usersRepository;
    private final TransactionsRepository transactionsRepository;
    private final ModelMapper mapper;

    public GetTransactionsFromAnUserService(UsersRepository usersRepository, TransactionsRepository transactionsRepository, ModelMapper mapper) {
        this.usersRepository = usersRepository;
        this.transactionsRepository = transactionsRepository;
        this.mapper = mapper;
    }

    @Transactional
    public TransactionsBalanceResponseDTO execute(String userEmail) {
        var transactionsBalanceDTO = new TransactionsBalanceResponseDTO();
        AppUser user = usersRepository.findByEmail(userEmail)
                .orElseThrow(() -> new NotFoundException(NotFoundException.userNotFound));

        transactionsBalanceDTO.transactions = buildTransactionsData(transactionsRepository.findByUserId(user.getId()));
        transactionsBalanceDTO.balance = calculateBalance(transactionsBalanceDTO.transactions);

        return  transactionsBalanceDTO;
    }

    private List<TransactionResponseDataDTO> buildTransactionsData(List<Transaction> transactions) {
        return transactions.stream()
                .map(t -> mapper.map(t, TransactionResponseDataDTO.class))
                .collect(Collectors.toList());
    }

    private BalanceDTO calculateBalance(List<TransactionResponseDataDTO> transactions) {
        var balance = new BalanceDTO();
        transactions.forEach((t) -> {
            if(t.type.equals(TransactionTypes.INCOME.getType())){
                balance.balance = balance.balance.add(t.value);
                balance.incomesSum = balance.incomesSum.add(t.value);
            } else {
                balance.balance = balance.balance.subtract(t.value);
                balance.outcomesSum = balance.outcomesSum.add(t.value);
            }
        });
        return balance;
    }
}
