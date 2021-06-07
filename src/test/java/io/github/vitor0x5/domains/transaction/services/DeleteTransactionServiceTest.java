package io.github.vitor0x5.domains.transaction.services;

import io.github.vitor0x5.domains.transaction.TransactionsBaseTest;
import io.github.vitor0x5.domains.transaction.repositories.TransactionsRepository;
import io.github.vitor0x5.domains.transaction.utils.mocks.TransactionMocksFactory;
import io.github.vitor0x5.shared.exceptions.types.BusinessException;
import io.github.vitor0x5.shared.exceptions.types.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DeleteTransactionServiceTest extends TransactionsBaseTest {
    UUID createdTransactionId;

    private DeleteTransactionService deleteTransactionServiceTest;

    @Autowired
    TransactionsRepository transactionsRepository;

    @BeforeEach
    public void setUp() {
        deleteTransactionServiceTest = new DeleteTransactionService(transactionsRepository);
        createdTransactionId = transactionsRepository.save(TransactionMocksFactory.mockIncomeTransaction1(user1, mapper)).getId();
    }

    @Test
    public void deleteTransaction() {
        deleteTransactionServiceTest.execute(createdTransactionId, user1.getEmail());
        assert transactionsRepository.findById(createdTransactionId).isEmpty();
    }

    @Test
    public void deleteTransactionWithInvalidUserEmail() {
        assertThrows(BusinessException.class, () ->
                deleteTransactionServiceTest.execute(createdTransactionId, "invalid@email.com"));
    }

    @Test
    public void deleteTransactionWithInvalidId() {
        assertThrows(NotFoundException.class, () ->
                deleteTransactionServiceTest.execute(UUID.randomUUID(), user1.getEmail()));
    }
}