package io.github.vitor0x5.domains.transaction.services;

import io.github.vitor0x5.domains.transaction.TransactionsBaseTest;
import io.github.vitor0x5.domains.transaction.dtos.TransactionResponseDataDTO;
import io.github.vitor0x5.domains.transaction.repositories.TransactionsRepository;
import io.github.vitor0x5.domains.transaction.utils.mocks.TransactionMocksFactory;
import io.github.vitor0x5.domains.user.repositories.UsersRepository;
import io.github.vitor0x5.shared.exceptions.types.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CreateTransactionServiceTest extends TransactionsBaseTest {
    private CreateTransactionService createTransactionService;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    TransactionsRepository transactionsRepository;

    @BeforeEach
    public void setUp() {
        createTransactionService = new CreateTransactionService(
                transactionsRepository,
                usersRepository,
                mapper);
    }

    @Test
    public void CreateTransactionForAValidUser() {
        var transactionDTO = TransactionMocksFactory.mockCreateIncomeDTO1();

        TransactionResponseDataDTO response = createTransactionService.execute(
                transactionDTO,
                user1.getId());
        checkTransactionResponseDataDTO(response, transactionDTO);
    }

    @Test
    public void CreateTransactionForAnIValidUser() {
        assertThrows(NotFoundException.class, () ->
                createTransactionService.execute(
                TransactionMocksFactory.mockCreateIncomeDTO1(),
                UUID.randomUUID()));
    }

}