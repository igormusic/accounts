package com.transactrules.accounts.configuration;


public enum BusinessDayCalculation {
    AnyDay(1),
    NextBusinessDay(2),
    PreviousBusinessDay(3),
    ClosestBusinessDayOrNext(4),
    NextBusinessDayThisMonthOrPrevious(5);

    private final int value;

     BusinessDayCalculation(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }


}
