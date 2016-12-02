package com.transactrules.accounts.runtime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.transactrules.accounts.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by 313798977 on 2016/11/11.
 */

@Entity
public class Transaction extends AbstractEntity {

    private Long transactionTypeId;
    private BigDecimal amount;

    @JsonIgnore
    @ManyToOne
    private Account account;

    private LocalDate actionDate;
    private LocalDate valueDate;

    Transaction(){

    }

    public Transaction(Long transactionTypeId, BigDecimal amount, Account account, LocalDate actionDate, LocalDate valueDate) {
        this.transactionTypeId = transactionTypeId;
        this.amount = amount;
        this.account = account;
        this.actionDate = actionDate;
        this.valueDate = valueDate;
    }

    public Long transactionTypeId() {
        return transactionTypeId;
    }

    public BigDecimal amount() {
        return amount;
    }

    public Account account() {
        return account;
    }

    public LocalDate actionDate() {
        return actionDate;
    }

    public LocalDate valueDate() {
        return valueDate;
    }
}
