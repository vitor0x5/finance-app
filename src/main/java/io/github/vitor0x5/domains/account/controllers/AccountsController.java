package io.github.vitor0x5.domains.account.controllers;

import io.github.vitor0x5.domains.account.dtos.AccountResponseDTO;
import io.github.vitor0x5.domains.account.dtos.CreateAccountDTO;
import io.github.vitor0x5.domains.account.services.CreateAccountService;
import io.github.vitor0x5.domains.account.services.GetAccountService;
import io.github.vitor0x5.domains.account.services.ListAccountsFromAnUserService;
import io.github.vitor0x5.domains.user.services.GetUserIdService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountsController {
    private final CreateAccountService createAccountService;
    private final GetAccountService getAccountService;
    private final ListAccountsFromAnUserService listAccountsFromAnUserService;
    private final GetUserIdService getUserIdService;

    public AccountsController(CreateAccountService createAccountService, GetAccountService getAccountService, ListAccountsFromAnUserService listAccountsFromAnUserService, GetUserIdService getUserIdService) {
        this.createAccountService = createAccountService;
        this.getAccountService = getAccountService;
        this.listAccountsFromAnUserService = listAccountsFromAnUserService;
        this.getUserIdService = getUserIdService;
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponseDTO createAccount(
            @RequestAttribute("userEmail") String userEmail,
            @Valid @RequestBody CreateAccountDTO accountDTO) {
        return createAccountService.execute(userEmail, accountDTO);
    }

    @GetMapping("/{id}")
    public AccountResponseDTO getAccount(
            @RequestAttribute("userEmail") String userEmail,
            @PathVariable UUID id
            ) {
        return getAccountService.execute(userEmail, id);
    }

    @GetMapping("/")
    public List<AccountResponseDTO> list(
            @RequestAttribute("userEmail") String userEmail,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "elements", defaultValue = "100") int elements
    ) {
        UUID userId = getUserIdService.execute(userEmail);
        return listAccountsFromAnUserService.execute(userId, page, elements);
    }
}
