package io.github.vitor0x5.domains.transaction.services;

import io.github.vitor0x5.domains.transaction.TransactionTypes;
import io.github.vitor0x5.domains.transaction.dtos.BalanceDTO;
import io.github.vitor0x5.domains.transaction.dtos.TransactionsBalanceResponseDTO;
import io.github.vitor0x5.domains.transaction.dtos.TransactionResponseDataDTO;
import io.github.vitor0x5.domains.transaction.entities.Transaction;
import io.github.vitor0x5.domains.transaction.repositories.TransactionsRepository;
import io.github.vitor0x5.shared.errors.types.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GetTransactionsFromAnUserService {
    private final TransactionsRepository transactionsRepository;
    private final ModelMapper mapper;

    public GetTransactionsFromAnUserService(
            TransactionsRepository transactionsRepository,
            ModelMapper mapper
    ) {
        this.transactionsRepository = transactionsRepository;
        this.mapper = mapper;
    }

    @Transactional
    public TransactionsBalanceResponseDTO execute(UUID userId, int page, int elements) {
        Pageable pageable = PageRequest.of(page, elements);

        List<Transaction> transactions = transactionsRepository
                .findByUserId(userId, pageable)
                .orElseThrow(()-> new NotFoundException(NotFoundException.transactionNotFound));

        return buildResponse(transactions);
    }

    private TransactionsBalanceResponseDTO buildResponse(List<Transaction> transactions) {
        var transactionsBalanceDTO = new TransactionsBalanceResponseDTO();
        transactionsBalanceDTO.transactions = mapToTransactionsBalanceDTO(transactions);
        transactionsBalanceDTO.transactions.sort(Comparator.comparing(t -> t.transactionDate));
        transactionsBalanceDTO.balance = calculateBalance(transactionsBalanceDTO.transactions);

        return transactionsBalanceDTO;
    }

    private List<TransactionResponseDataDTO> mapToTransactionsBalanceDTO(List<Transaction> transactions) {
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
