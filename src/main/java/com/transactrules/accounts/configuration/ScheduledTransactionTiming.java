package com.transactrules.accounts.configuration;

public enum ScheduledTransactionTiming {

    StartOfDay(1),
    EndOfDay(2);

    private final int value;

    ScheduledTransactionTiming(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
