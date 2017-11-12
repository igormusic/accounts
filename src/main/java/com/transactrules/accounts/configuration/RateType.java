package com.transactrules.accounts.configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.transactrules.accounts.NamedAbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class RateType extends NamedAbstractEntity {
    @JsonIgnore
    @ManyToOne
    private AccountType accountType;


    RateType(){}

    RateType(AccountType accountType, String name) {
        super(name);
        this.accountType = accountType;
    }

    public AccountType accountType() {
        return accountType;
    }
}
