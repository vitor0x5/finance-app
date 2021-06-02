package io.github.vitor0x5.domains.transaction.controllers;

import io.github.vitor0x5.domains.transaction.dtos.CreateTransactionDTO;
import io.github.vitor0x5.domains.transaction.dtos.TransactionResponseDataDTO;
import io.github.vitor0x5.domains.transaction.dtos.TransactionsBalanceResponseDTO;
import io.github.vitor0x5.domains.transaction.services.CreateTransactionService;
import io.github.vitor0x5.domains.transaction.services.DeleteTransactionService;
import io.github.vitor0x5.domains.transaction.services.GetTransactionsFromAnUserService;
import io.github.vitor0x5.domains.transaction.services.UpdateTransactionService;
import io.github.vitor0x5.domains.user.services.GetUserIdService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final CreateTransactionService createTransactionService;
    private final UpdateTransactionService updateTransactionService;
    private final GetTransactionsFromAnUserService getTransactionsFromAnUserService;
    private final DeleteTransactionService deleteTransactionService;
    private final GetUserIdService getUserIdService;

    public TransactionController(
            CreateTransactionService createTransactionService,
            UpdateTransactionService updateTransactionService, DeleteTransactionService deleteTransactionService,
            GetTransactionsFromAnUserService getTransactionsFromAnUserService,
            GetUserIdService getUserIdService) {
        this.createTransactionService = createTransactionService;
        this.updateTransactionService = updateTransactionService;
        this.deleteTransactionService = deleteTransactionService;
        this.getTransactionsFromAnUserService = getTransactionsFromAnUserService;
        this.getUserIdService = getUserIdService;
    }

    @PostMapping("/new")
    public TransactionResponseDataDTO createTransaction(
            @Valid @RequestBody CreateTransactionDTO transactionData,
            @RequestAttribute("userEmail") String userEmail
    ) {
        UUID userId = getUserId(userEmail);
        return createTransactionService.execute(transactionData, userId);
    }

    @PutMapping("/{id}")
    public TransactionResponseDataDTO updateTransaction(
            @Valid @RequestBody CreateTransactionDTO transactionData,
            @PathVariable UUID id,
            @RequestAttribute("userEmail") String userEmail
    ) {
        UUID userId = getUserId(userEmail);
        return updateTransactionService.execute(id, userId, transactionData);
    }

    @GetMapping("/")
    public TransactionsBalanceResponseDTO getAllTransactionsFromAnUser(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "elements", defaultValue = "100") int elements,
            @RequestAttribute("userEmail") String userEmail
    ) {
        UUID userId = getUserId(userEmail);
        return getTransactionsFromAnUserService.execute(userId, page, elements);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(
            @PathVariable UUID id,
            @RequestAttribute("userEmail") String userEmail
    ) {
        deleteTransactionService.execute(id, userEmail);
    }

    private UUID getUserId(String userEmail) {
        return getUserIdService.execute(userEmail);
    }
}
