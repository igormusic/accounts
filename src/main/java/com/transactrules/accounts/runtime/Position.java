package com.transactrules.accounts.runtime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.transactrules.accounts.AbstractEntity;
import com.transactrules.accounts.configuration.TransactionOperation;
import com.transactrules.accounts.runtime.accounts.Account;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

/**
 * Created by 313798977 on 2016/11/11.
 */
@Entity
public class Position extends AbstractEntity {

    private BigDecimal amount;

    @JsonIgnore
    @ManyToOne
    public Account account;

    Position(){

    }

    public Position(Long positionTypeId, Account account) {
        this.amount = BigDecimal.ZERO;
        this.account = account;
    }


    public void applyOperation(TransactionOperation operation, BigDecimal value){
        switch (operation) {
            case Subtract:
                amount = amount.subtract(value);
                break;
            case Add:
                amount = amount.add(value);
                break;
            default:
                break;
        }
    }

    public BigDecimal add(BigDecimal value) {
        BigDecimal result = amount.add(value);

        amount = value;

        return amount;
    }

    public BigDecimal subtract(BigDecimal value) {
        BigDecimal result = amount.subtract(value);

        amount = value;

        return amount;
    }

    public BigDecimal amount(){
        return  amount;
    }
}
