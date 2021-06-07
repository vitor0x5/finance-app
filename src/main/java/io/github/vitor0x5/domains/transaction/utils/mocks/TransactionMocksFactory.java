package io.github.vitor0x5.domains.transaction.utils.mocks;

import io.github.vitor0x5.domains.transaction.TransactionTypes;
import io.github.vitor0x5.domains.transaction.dtos.CreateTransactionDTO;
import io.github.vitor0x5.domains.transaction.entities.Transaction;
import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.shared.mappers.MapperProducer;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionMocksFactory {

    public static Transaction mockIncomeTransaction1(AppUser user, ModelMapper mapper) {
        return mockTransaction(mockCreateIncomeDTO1(), user, mapper);
    }

    public static CreateTransactionDTO mockCreateIncomeDTO1() {
        CreateTransactionDTO transactionDTO = new CreateTransactionDTO();
        transactionDTO.transactionDate = LocalDate.of(2021, 1, 1);
        transactionDTO.type = TransactionTypes.INCOME.getType();
        transactionDTO.description = "Test income";
        transactionDTO.source = "Test Bank";
        transactionDTO.value = BigDecimal.TEN;
        return transactionDTO;
    }

    public static Transaction mockOutcomeTransaction1(AppUser user, ModelMapper mapper) {
        return mockTransaction(mockCreateOutcomeDTO1(), user, mapper);
    }

    public static CreateTransactionDTO mockCreateOutcomeDTO1() {
        CreateTransactionDTO transactionDTO = new CreateTransactionDTO();
        transactionDTO.transactionDate = LocalDate.of(2021, 1, 1);
        transactionDTO.type = TransactionTypes.OUTCOME.getType();
        transactionDTO.description = "Test outcome";
        transactionDTO.source = "Test Bank";
        transactionDTO.value = BigDecimal.ONE;
        return transactionDTO;
    }

    public static Transaction mockTransaction(CreateTransactionDTO transactionDTO, AppUser user, ModelMapper mapper) {
        var transaction = mapper.map(transactionDTO, Transaction.class);

        transaction.setType(TransactionTypes.fromString(transactionDTO.type));
        transaction.setUser(user);
        return transaction;
    }
}
