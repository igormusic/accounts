package com.transactrules.accounts;

import com.transactrules.accounts.configuration.*;


/**
 * Created by Administrator on 11/26/2016.
 */

public class AccountTypeFactory {


    public static AccountType createSavingsAccountType()
    {
        AccountType accountType = new AccountType( "SavingsAccount");

        PositionType currentPosition = accountType.addPositionType( "Current");
        PositionType interestAccruedPosition = accountType.addPositionType( "InterestAccrued" );

        TransactionType depositTransaction = accountType.addTransactionType("Deposit", false);

        depositTransaction.getTransactionRules().add(new TransactionRuleType(currentPosition, depositTransaction, TransactionOperation.Add));

        TransactionType withdrawalTransaction = accountType.addTransactionType("Withdrawal",false);

        withdrawalTransaction.getTransactionRules().add(new TransactionRuleType(currentPosition, withdrawalTransaction, TransactionOperation.Subtract));

        TransactionType interestAccruedTransaction = accountType.addTransactionType("InterestAccrued", true);

        interestAccruedTransaction.getTransactionRules().add(new TransactionRuleType(interestAccruedPosition, interestAccruedTransaction, TransactionOperation.Add));

        TransactionType interestCapitalizedTransaction = accountType.addTransactionType("InterestCapitalized", false);

        interestCapitalizedTransaction.getTransactionRules().add(new TransactionRuleType(interestAccruedPosition,interestCapitalizedTransaction, TransactionOperation.Subtract ));
        interestCapitalizedTransaction.getTransactionRules().add(new TransactionRuleType(currentPosition,interestCapitalizedTransaction, TransactionOperation.Add ));

        return accountType;
    }
}
