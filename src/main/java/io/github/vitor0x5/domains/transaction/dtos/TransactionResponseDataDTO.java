package io.github.vitor0x5.domains.transaction.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class TransactionResponseDataDTO {
    public UUID id;
    public String source;
    public String description;
    public String type;
    public BigDecimal value;
    public LocalDate transactionDate;
}
