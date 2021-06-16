package io.github.vitor0x5.domains.transaction.services;

import io.github.vitor0x5.domains.transaction.TransactionTypes;
import io.github.vitor0x5.domains.transaction.dtos.CreateTransactionDTO;
import io.github.vitor0x5.domains.transaction.dtos.TransactionResponseDataDTO;
import io.github.vitor0x5.domains.transaction.entities.Transaction;
import io.github.vitor0x5.domains.transaction.repositories.TransactionsRepository;
import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.repositories.UsersRepository;
import io.github.vitor0x5.shared.exceptions.types.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CreateTransactionService {
    private final TransactionsRepository transactionsRepository;
    private final UsersRepository usersRepository;
    private final ModelMapper mapper;

    public CreateTransactionService(
            TransactionsRepository transactionsRepository,
            UsersRepository usersRepository,
            ModelMapper mapper
    ) {
        this.transactionsRepository = transactionsRepository;
        this.usersRepository = usersRepository;
        this.mapper = mapper;
    }

    @Transactional
    public TransactionResponseDataDTO execute(CreateTransactionDTO transactionData, UUID userId) {
        AppUser user = usersRepository.findById(userId)
                .orElseThrow(() -> {
                    throw new NotFoundException(NotFoundException.USER);
                });

        Transaction transaction = mapper.map(transactionData, Transaction.class);
        transaction.setUser(user);
        transaction.setType(TransactionTypes.fromString(transactionData.type));
        transactionsRepository.save(transaction);

        return mapper.map(transaction, TransactionResponseDataDTO.class);
    }
}
