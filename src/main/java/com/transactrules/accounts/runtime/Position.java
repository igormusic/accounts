package com.transactrules.accounts.runtime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.transactrules.accounts.AbstractEntity;
import com.transactrules.accounts.configuration.TransactionOperation;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

/**
 * Created by 313798977 on 2016/11/11.
 */
@Entity
public class Position extends AbstractEntity {

    private Long positionTypeId;
    private BigDecimal amount;

    @JsonIgnore
    @ManyToOne
    private Account account;

    Position(){

    }

    public Position(Long positionTypeId, Account account) {
        this.positionTypeId = positionTypeId;
        this.amount = BigDecimal.ZERO;
        this.account = account;
    }

    public Long getPositionTypeId() {
        return positionTypeId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Account getAccount() {
        return account;
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

}
