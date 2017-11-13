package com.transactrules.accounts.runtime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.transactrules.accounts.AbstractEntity;
import com.transactrules.accounts.runtime.accounts.Account;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class AmountValue extends AbstractEntity {
    public BigDecimal amount;

    @JsonIgnore
    @ManyToOne
    public Account account;

    AmountValue(){

    }

    public AmountValue(Account account, BigDecimal value) {
        this.amount = value;
        this.account = account;
    }

    public BigDecimal amount(){
        return  amount;
    }
}
