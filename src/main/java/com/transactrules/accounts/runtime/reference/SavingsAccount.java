package com.transactrules.accounts.runtime.reference;

import com.transactrules.accounts.configuration.PositionType;
import com.transactrules.accounts.configuration.TransactionType;
import com.transactrules.accounts.runtime.Position;
import com.transactrules.accounts.runtime.Transaction;
import com.transactrules.accounts.runtime.TransactionClient;

import java.util.Optional;


public class SavingsAccount extends TransactionClient {

    private Position current;

    public Position current() {

        if (current == null){
            Optional<PositionType> positionType = accountType.getPositionTypeByName("Current");

            if(positionType.isPresent()){
                this.current= this.account.initializePosition(positionType.get());
            }
        }
        return this.current;
    }

    private Position interestAccrued;

    public Position interestAccrued() {
        if (interestAccrued == null){
            Optional<PositionType> positionType = accountType.getPositionTypeByName("InterestAccrued");

            if(positionType.isPresent()){
                this.interestAccrued= this.account.initializePosition(positionType.get());
            }
        }
        return this.interestAccrued;
    }

    @Override
    public void processTransaction(Transaction transaction) {
        Optional<TransactionType> transactionType = this.accountType.getTransactionType(transaction.transactionTypeId());


        switch (transactionType.get().name())
        {
            case "Deposit":
                current().add(transaction.amount());
            case "Withdrawal":
                current().subtract(transaction.amount());
            case "InterestAccrued":
                interestAccrued().add(transaction.amount());
            case "InterestCapitalized":
                interestAccrued().subtract(transaction.amount());
                current().add(transaction.amount());
            default:

        }
    }

    @Override
    public void startOfDay() {

    }

    @Override
    public void endOfOfDay() {

    }

    @Override
    public void onDataChanged() {

    }

    @Override
    public void calculateInstaments() {

    }


}
