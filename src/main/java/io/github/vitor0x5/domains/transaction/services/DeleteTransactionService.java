package io.github.vitor0x5.domains.transaction.services;

import io.github.vitor0x5.domains.transaction.entities.Transaction;
import io.github.vitor0x5.domains.transaction.repositories.TransactionsRepository;
import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.repositories.UsersRepository;
import io.github.vitor0x5.shared.errors.types.BusinessException;
import io.github.vitor0x5.shared.errors.types.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DeleteTransactionService {
    private final TransactionsRepository transactionsRepository;

    public DeleteTransactionService(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    @Transactional
    public void execute(UUID id, String userEmail){
        Transaction transaction = transactionsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NotFoundException.transactionNotFound));

        if(!transaction.getUser().getEmail().equals(userEmail)){
            throw new BusinessException(BusinessException.incorrectUser);
        }
        transactionsRepository.deleteById(id);
    }
}
