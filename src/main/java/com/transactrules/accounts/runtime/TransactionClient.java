package com.transactrules.accounts.runtime;

import com.transactrules.accounts.configuration.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Set;

public abstract class TransactionClient
{
    private Account account;
    private LocalDate actionDate;
    private LocalDate valueDate;


    public Set<Transaction> getTransactions() {

        return account.getTransactions();
    }

    public Set<Position> getPositions() {

        return account.getPositions();
    }

    public void initialize(Account account) {
        this.account = account;

        actionDate = LocalDate.now();
        valueDate = LocalDate.now();
    }

    public abstract void processTransaction(Transaction transaction);

    public abstract void StartOfDay();
    public abstract void EndOfOfDay();

    public abstract void OnDataChanged();

    public abstract void CalculateInstaments();

    public Transaction createTransaction(TransactionType transactionType, BigDecimal amount) {

        Transaction transaction = new Transaction(transactionType.getId(),amount,account, actionDate, valueDate);

        processTransaction(transaction);

        account.getTransactions().add(transaction);

        return transaction;
    }
}


