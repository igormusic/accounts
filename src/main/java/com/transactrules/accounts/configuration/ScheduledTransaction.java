package com.transactrules.accounts.configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.transactrules.accounts.NamedAbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ScheduledTransaction extends NamedAbstractEntity {

    @JsonIgnore
    @ManyToOne
    private  AccountType accountType;

    private ScheduledTransactionTiming timing;
    @ManyToOne
    private ScheduleType scheduleType;
    @ManyToOne
    private DateType dateType;
    @ManyToOne
    private TransactionType transactionType;
    @Column( length = 64000)
    private String amountExpression;
    private Integer sequence;

    public ScheduledTransaction(){

    }

   public ScheduledTransaction(
           AccountType accountType,
           String name,
           ScheduledTransactionTiming timing,
           DateType dateType,
           ScheduleType scheduleType,
           TransactionType transactionType,
           String amountExpression,
           Integer sequence
           ){
        super(name);
        this.accountType = accountType;
        this.timing = timing;
        this.dateType = dateType;
        this.scheduleType = scheduleType;
        this.transactionType = transactionType;
        this.amountExpression = amountExpression;
        this.sequence = sequence;
   }
}
