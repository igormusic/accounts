package com.transactrules.accounts.configuration;

/**
 * Created by 313798977 on 2016/11/12.
 */
public enum TransactionOperation {
    Add(1),
    Subtract(-1);

    @SuppressWarnings("unused")
    private int value = 0;

    TransactionOperation(int value) {
        this.value = value;
    }

}
