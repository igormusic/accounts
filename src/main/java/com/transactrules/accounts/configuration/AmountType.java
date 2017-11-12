package com.transactrules.accounts.configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.transactrules.accounts.NamedAbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class AmountType extends NamedAbstractEntity {

    @JsonIgnore
    @ManyToOne
    private AccountType accountType;

    private Boolean isValueDated;

    AmountType(){}

    AmountType(AccountType accountType, String name, Boolean isValueDated) {
        super(name);
        this.accountType = accountType;
        this.isValueDated = isValueDated;
    }

    public AccountType accountType() {
        return accountType;
    }

    public Boolean isValueDated() {
        return this.isValueDated;
    }


}
