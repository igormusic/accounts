package com.transactrules.accounts.configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.transactrules.accounts.NamedAbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Administrator on 11/27/2016.
 */
@Entity
public class DateType extends NamedAbstractEntity {

    @JsonIgnore
    @ManyToOne
    private AccountType accountType;


    DateType(){}

    DateType(AccountType accountType, String name) {
        super(name);
        this.accountType = accountType;
    }

    public AccountType accountType() {
        return accountType;
    }

}
