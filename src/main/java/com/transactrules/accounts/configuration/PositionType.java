package com.transactrules.accounts.configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.transactrules.accounts.NamedAbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Define configuration for position type
 */

@Entity
public class PositionType  extends NamedAbstractEntity {

    @JsonIgnore
    @ManyToOne
    private AccountType accountType;

    PositionType() {

    }

    public PositionType(AccountType accountType, String name) {
        super(name);
        this.accountType = accountType;
    }

    public AccountType getAccountType() {
        return accountType;
    }

}
