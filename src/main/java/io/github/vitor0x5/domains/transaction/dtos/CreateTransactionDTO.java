package io.github.vitor0x5.domains.transaction.dtos;

import io.github.vitor0x5.domains.transaction.utils.TransactionTypeValidatorAnnotation.TransactionTypeValidation;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateTransactionDTO {
    @NotNull(message = "Source can´t be null")
    public String source;

    @NotNull(message = "Description can´t be null")
    public String description;

    @NotNull(message = "Type can´t be null")
    @TransactionTypeValidation
    public String type;

    @NotNull(message = "Value can´t be null")
    public BigDecimal value;

    @NotNull(message = "Transaction date can´t be null")
    public LocalDate transactionDate;
}
