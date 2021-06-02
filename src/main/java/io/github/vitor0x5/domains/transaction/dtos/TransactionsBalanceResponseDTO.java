package io.github.vitor0x5.domains.transaction.dtos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TransactionsBalanceResponseDTO {
    public List<TransactionResponseDataDTO> transactions = new ArrayList<>();
    public BalanceDTO balance;
}
