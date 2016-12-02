package com.transactrules.accounts.configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.transactrules.accounts.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by 313798977 on 2016/11/12.
 */

@Entity
public class TransactionRuleType extends AbstractEntity {


    @ManyToOne
    @JsonIgnore
    private PositionType posititonType;

    @ManyToOne
    @JsonIgnore
    private TransactionType transactionType;
    private TransactionOperation transactionOperation;

    public TransactionRuleType(PositionType posititonType, TransactionType transactionType, TransactionOperation transactionOperation) {
        this.posititonType = posititonType;
        this.transactionType = transactionType;
        this.transactionOperation = transactionOperation;
    }

    public PositionType getPosititonType() {
        return posititonType;
    }

    public Long positionTypeId() {
        return posititonType.id();
    }

    public TransactionType transactionType() {
        return transactionType;
    }

    public TransactionOperation transactionOperation() {
        return transactionOperation;
    }


}
