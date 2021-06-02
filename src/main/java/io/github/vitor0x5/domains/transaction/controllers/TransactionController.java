package io.github.vitor0x5.domains.transaction.controllers;

import io.github.vitor0x5.domains.transaction.dtos.CreateTransactionDTO;
import io.github.vitor0x5.domains.transaction.dtos.TransactionResponseDataDTO;
import io.github.vitor0x5.domains.transaction.dtos.TransactionsBalanceResponseDTO;
import io.github.vitor0x5.domains.transaction.services.CreateTransactionService;
import io.github.vitor0x5.domains.transaction.services.DeleteTransactionService;
import io.github.vitor0x5.domains.transaction.services.GetTransactionsFromAnUserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final CreateTransactionService createTransactionService;
    private final DeleteTransactionService deleteTransactionService;
    private final GetTransactionsFromAnUserService getTransactionsFromAnUserService;

    public TransactionController(
            CreateTransactionService createTransactionService,
            DeleteTransactionService deleteTransactionService,
            GetTransactionsFromAnUserService getTransactionsFromAnUserService
    ) {
        this.createTransactionService = createTransactionService;
        this.deleteTransactionService = deleteTransactionService;
        this.getTransactionsFromAnUserService = getTransactionsFromAnUserService;
    }

    @PostMapping("/new")
    public TransactionResponseDataDTO createTransaction(
            @Valid @RequestBody CreateTransactionDTO transactionData,
            @RequestAttribute("userEmail") String userEmail
            ) {
        return createTransactionService.execute(transactionData, userEmail);
    }

    @GetMapping("/")
    public TransactionsBalanceResponseDTO getAllTransactionsFromAnUser(
            @RequestAttribute("userEmail") String userEmail
    ) {
      return getTransactionsFromAnUserService.execute(userEmail);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(
            @PathVariable UUID id,
            @RequestAttribute("userEmail") String userEmail
    ) {
        deleteTransactionService.execute(id, userEmail);
    }
}
