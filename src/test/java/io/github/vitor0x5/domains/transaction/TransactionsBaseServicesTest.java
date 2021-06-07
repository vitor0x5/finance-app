package io.github.vitor0x5.domains.transaction;

import io.github.vitor0x5.domains.configurations.BaseServicesTest;
import io.github.vitor0x5.domains.transaction.dtos.CreateTransactionDTO;
import io.github.vitor0x5.domains.transaction.dtos.TransactionResponseDataDTO;
import io.github.vitor0x5.domains.transaction.entities.Transaction;
import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.repositories.UsersRepository;
import io.github.vitor0x5.domains.user.utils.mocks.UserMocksFactory;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionsBaseServicesTest extends BaseServicesTest {

    @Autowired
    private UsersRepository usersRepository;

    public AppUser user1;

    @BeforeEach
    public void mockUser() {
        AppUser userToSave = UserMocksFactory.mockAppUser1();
        user1 = usersRepository.save(userToSave);
    }

    public void checkTransactionResponseDataDTO(TransactionResponseDataDTO response, CreateTransactionDTO transactionDTO) {
        assert response.description.equals(transactionDTO.description);
        assert response.type.equals(transactionDTO.type);
        assert response.transactionDate.equals(transactionDTO.transactionDate);
        assert response.source.equals(transactionDTO.source);
        assert response.value.equals(transactionDTO.value);
    }

    public void checkTransactionResponseDataDTO(TransactionResponseDataDTO response, Transaction transaction) {
        CreateTransactionDTO dto = mapper.map(transaction, CreateTransactionDTO.class);
        dto.type = transaction.getType().getType();
        checkTransactionResponseDataDTO(response, dto);
    }
}
