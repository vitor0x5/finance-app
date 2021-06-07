package io.github.vitor0x5.domains.transaction.services;

import io.github.vitor0x5.domains.transaction.TransactionTypes;
import io.github.vitor0x5.domains.transaction.TransactionsBaseTest;
import io.github.vitor0x5.domains.transaction.dtos.CreateTransactionDTO;
import io.github.vitor0x5.domains.transaction.dtos.TransactionResponseDataDTO;
import io.github.vitor0x5.domains.transaction.entities.Transaction;
import io.github.vitor0x5.domains.transaction.repositories.TransactionsRepository;
import io.github.vitor0x5.domains.transaction.utils.mocks.TransactionMocksFactory;
import io.github.vitor0x5.shared.exceptions.types.BusinessException;
import io.github.vitor0x5.shared.exceptions.types.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

class UpdateTransactionServiceTest extends TransactionsBaseTest {
    private Transaction createdTransaction1;
    private CreateTransactionDTO updatedTransactionDTO;

    private UpdateTransactionService updateTransactionService;

    @Autowired
    TransactionsRepository transactionsRepository;

    @BeforeEach
    public void setUp() {
        updateTransactionService = new UpdateTransactionService(transactionsRepository, mapper);
        createdTransaction1 = transactionsRepository
                .save(TransactionMocksFactory.mockIncomeTransaction1(user1, mapper));

        updatedTransactionDTO = mapper.map(createdTransaction1, CreateTransactionDTO.class);
        updatedTransactionDTO.description = "Updated Description";
        updatedTransactionDTO.type = TransactionTypes.OUTCOME.getType();
    }

    @Test
    public void updateTransaction() {
        TransactionResponseDataDTO response = updateTransactionService
                .execute(createdTransaction1.getId(), user1.getId(), updatedTransactionDTO);

        checkTransactionResponseDataDTO(response, updatedTransactionDTO);
    }

    @Test
    public void updateTransactionWithInvalidId() {
        assertThrows(NotFoundException.class, () ->
                updateTransactionService.execute(
                        UUID.randomUUID(),
                        user1.getId(),
                        updatedTransactionDTO
                ));
    }

    @Test
    public void updateTransactionWithInvalidUserId() {
        assertThrows(BusinessException.class, () ->
                updateTransactionService.execute(
                        createdTransaction1.getId(),
                        UUID.randomUUID(),
                        updatedTransactionDTO
                ));
    }

}