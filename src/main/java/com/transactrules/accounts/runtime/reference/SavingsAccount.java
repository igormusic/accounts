package com.transactrules.accounts.runtime.reference;

import com.transactrules.accounts.runtime.Position;
import com.transactrules.accounts.runtime.Transaction;
import com.transactrules.accounts.runtime.TransactionClient;


public class SavingsAccount extends TransactionClient {

    private Position current;

    public Position current() {

        return this.current;
    }

    @Override
    public void processTransaction(Transaction transaction) {

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
