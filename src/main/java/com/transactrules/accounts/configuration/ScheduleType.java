package com.transactrules.accounts.configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.transactrules.accounts.NamedAbstractEntity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

/**
 * Created by Administrator on 11/27/2016.
 */
public class ScheduleType extends NamedAbstractEntity {
    @JsonIgnore
    @ManyToOne
    private AccountType accountType;

    @Enumerated(EnumType.STRING)
    private ScheduleType scheduleType;

    @Enumerated(EnumType.STRING)
    private ScheduleEndType scheduleEndType;

    ScheduleType(){}

    ScheduleType(AccountType accountType, String name) {
        super(name);
        this.accountType = accountType;
    }

    public AccountType getAccountType() {
        return accountType;
    }
}
