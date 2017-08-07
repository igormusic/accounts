package com.transactrules.accounts.runtime.accounts;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateTransactionCommand {
    @TargetAggregateIdentifier
    private String accountId;

    private Long transactionTypeId;
    private BigDecimal amount;
    private LocalDate actionDate;
    private LocalDate valueDate;
}
