package io.github.vitor0x5.domains.account.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public class AccountResponseDTO {
    public UUID id;
    public String nickname;
    public String bank;
    public BigDecimal balance;
}
