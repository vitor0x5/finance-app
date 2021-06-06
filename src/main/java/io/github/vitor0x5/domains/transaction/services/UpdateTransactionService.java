package io.github.vitor0x5.domains.transaction.services;

import io.github.vitor0x5.domains.transaction.TransactionTypes;
import io.github.vitor0x5.domains.transaction.dtos.CreateTransactionDTO;
import io.github.vitor0x5.domains.transaction.dtos.TransactionResponseDataDTO;
import io.github.vitor0x5.domains.transaction.entities.Transaction;
import io.github.vitor0x5.domains.transaction.repositories.TransactionsRepository;
import io.github.vitor0x5.shared.exceptions.types.BusinessException;
import io.github.vitor0x5.shared.exceptions.types.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UpdateTransactionService {
    private final TransactionsRepository transactionsRepository;
    private final ModelMapper mapper;

    public UpdateTransactionService(TransactionsRepository transactionsRepository, ModelMapper mapper) {
        this.transactionsRepository = transactionsRepository;
        this.mapper = mapper;
    }

    @Transactional
    public TransactionResponseDataDTO execute(
            UUID id,
            UUID userId,
            CreateTransactionDTO transactionData
    ) {
        Transaction oldTransaction = transactionsRepository.findOneById(id)
                .orElseThrow(() -> new NotFoundException(NotFoundException.userNotFound));

        if(!oldTransaction.getUser().getId().equals(userId)){
            throw new BusinessException(BusinessException.incorrectUser);
        }

        Transaction transaction = mapper.map(transactionData, Transaction.class);
        transaction.setId(id);
        transaction.setUser(oldTransaction.getUser());
        transaction.setType(TransactionTypes.fromString(transactionData.type));
        transactionsRepository.save(transaction);

        return mapper.map(transaction, TransactionResponseDataDTO.class);
    }
}
