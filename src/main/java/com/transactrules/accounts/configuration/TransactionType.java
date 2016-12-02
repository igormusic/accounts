package com.transactrules.accounts.configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.transactrules.accounts.NamedAbstractEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Define configuration for transaction type
 */

@Entity
public class TransactionType extends NamedAbstractEntity {

    @JsonIgnore
    @ManyToOne
    private AccountType accountType;
    private boolean maximumPrecision;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<TransactionRuleType> transactionRules;

    public TransactionType(AccountType accountType,String name,  boolean hasMaximumPrecission) {
        super(name);
        this.accountType = accountType;
        this.maximumPrecision = hasMaximumPrecission;
        this.transactionRules = new HashSet<>();
    }

    TransactionType(){

    }

    public AccountType getAccountType() {
        return accountType;
    }

    public boolean isMaximumPrecision() {
        return maximumPrecision;
    }

    public Set<TransactionRuleType> getTransactionRules() {
        return transactionRules;
    }

    public void addRule(PositionType positionType, TransactionOperation operation) {
        transactionRules.add(new TransactionRuleType(positionType, this, operation));
    }
}
