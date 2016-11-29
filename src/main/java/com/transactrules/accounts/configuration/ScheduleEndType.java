package com.transactrules.accounts.configuration;

/**
 * Created by Administrator on 11/27/2016.
 */
public enum ScheduleEndType {
    NoEnd(1),
    EndDate(2),
    Repeats(3);

    private final int value;

    private ScheduleEndType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
