package io.github.vitor0x5.domains.account.dtos;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CreateAccountDTO {
    public String nickname;

    @NotNull(message = "The bank can't be null")
    public String bank;

    @NotNull(message = "The balance can't be null")
    public BigDecimal balance;
}
