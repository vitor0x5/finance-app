package io.github.vitor0x5.domains.transaction.services;

import io.github.vitor0x5.domains.transaction.TransactionsBaseTest;
import io.github.vitor0x5.domains.transaction.dtos.TransactionResponseDataDTO;
import io.github.vitor0x5.domains.transaction.dtos.TransactionsBalanceResponseDTO;
import io.github.vitor0x5.domains.transaction.entities.Transaction;
import io.github.vitor0x5.domains.transaction.repositories.TransactionsRepository;
import io.github.vitor0x5.domains.transaction.utils.mocks.TransactionMocksFactory;
import io.github.vitor0x5.shared.exceptions.types.BusinessException;
import io.github.vitor0x5.shared.exceptions.types.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.UUID;

class GetTransactionsFromAnUserServiceTest extends TransactionsBaseTest {
    private Transaction createdTransaction1;
    private Transaction createdTransaction2;

    private GetTransactionsFromAnUserService getTransactionsFromAnUserService;

    @Autowired
    TransactionsRepository transactionsRepository;

    @BeforeEach
    public void setUp() {
        getTransactionsFromAnUserService = new GetTransactionsFromAnUserService(transactionsRepository, mapper);
        createdTransaction1 = transactionsRepository
                .save(TransactionMocksFactory.mockIncomeTransaction1(user1, mapper));

        createdTransaction2 = transactionsRepository
                .save(TransactionMocksFactory.mockOutcomeTransaction1(user1, mapper));
    }

    @Test
    public void getTransactionsFromAnUser() {
        TransactionsBalanceResponseDTO balance =  getTransactionsFromAnUserService
                .execute(user1.getId(), 0, 2);

        assertThatBalanceIsCorrect(balance);
    }

    @Test
    public void getTransactionsWitnInvalidUserId() {
        assertThrows(NotFoundException.class, () -> getTransactionsFromAnUserService
                .execute(UUID.randomUUID(), 0, 2));
    }

    private void assertThatBalanceIsCorrect(TransactionsBalanceResponseDTO responseDTO) {
        TransactionResponseDataDTO transaction1 = mapper.map(createdTransaction1, TransactionResponseDataDTO.class);
        transaction1.type = createdTransaction1.getType().getType();
        TransactionResponseDataDTO transaction2 = mapper.map(createdTransaction2, TransactionResponseDataDTO.class);
        transaction1.type = createdTransaction2.getType().getType();

        checkTransactionResponseDataDTO(responseDTO.transactions.get(0), createdTransaction1);
        checkTransactionResponseDataDTO(responseDTO.transactions.get(1), createdTransaction2);

        BigDecimal correctBalance = transaction1.value.subtract(transaction2.value);
        assert responseDTO.balance.balance.equals(correctBalance);
    }
}