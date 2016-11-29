package com.transactrules.accounts.configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.transactrules.accounts.NamedAbstractEntity;

import javax.persistence.ManyToOne;

/**
 * Created by Administrator on 11/27/2016.
 */
public class DateType extends NamedAbstractEntity {

    @JsonIgnore
    @ManyToOne
    private AccountType accountType;

    DateType(){}

    DateType(AccountType accountType, String name) {
        this.accountType = accountType;
        this.name = name;
    }

    public AccountType getAccountType() {
        return accountType;
    }

}
