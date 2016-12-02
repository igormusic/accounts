package com.transactrules.accounts.runtime;

import com.transactrules.accounts.configuration.AccountType;
import com.transactrules.accounts.configuration.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public abstract class TransactionClient
{
    protected Account account;
    protected AccountType accountType;
    protected LocalDate actionDate;
    protected LocalDate valueDate;


    public Set<Transaction> transactions() {

        return account.transactions();
    }

    public Set<Position> positions() {

        return account.positions();
    }

    public void initialize(Account account, AccountType accountType) {
        this.account = account;
        this.accountType = accountType;
        actionDate = LocalDate.now();
        valueDate = LocalDate.now();
    }

    public abstract void processTransaction(Transaction transaction);

    public abstract void startOfDay();
    public abstract void endOfOfDay();

    public abstract void onDataChanged();

    public abstract void calculateInstaments();

    public Transaction createTransaction(TransactionType transactionType, BigDecimal amount) {

        Transaction transaction = new Transaction(transactionType.id(),amount,account, actionDate, valueDate);

        processTransaction(transaction);

        account.addTransaction(transaction);

        return transaction;
    }

    public void forecast(LocalDate futureDate)
    {
        LocalDate originalValueDate = valueDate;

        LocalDate iterator = originalValueDate;

        if (!account.isActive())
        {
            startOfDay();
        }

        while (valueDate.isBefore(futureDate) || valueDate.isEqual(futureDate) )
        {
            endOfOfDay();

            valueDate = valueDate.plusDays(1);

            startOfDay();
        }

        valueDate = originalValueDate;
    }

    /*public void SetFutureInstalmentValue(string instalmentType, ScheduledTransactionTiming timing, decimal value)
    {
        foreach (var instalment in Account.GetInstalments(instalmentType))
        {
            if (!instalment.HasFixedValue)
            {
                if ( (timing == ScheduledTransactionTiming.StartOfDay && instalment.ValueDate > SessionState.Current.ValueDate)
                        || (timing == ScheduledTransactionTiming.EndOfDay && instalment.ValueDate >= SessionState.Current.ValueDate))
                {
                    instalment.Amount = value;
                }
            }
        }
    }*/
}


