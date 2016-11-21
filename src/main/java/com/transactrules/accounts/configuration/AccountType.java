package com.transactrules.accounts.configuration;


import com.transactrules.accounts.NamedAbstractEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Aggregate root for account configuration
 */

@Entity
public class AccountType extends NamedAbstractEntity {

    @OneToMany(cascade = CascadeType.ALL)
    private Set<PositionType> positionTypes = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    private Set<TransactionType> transactionTypes = new HashSet<>();

    AccountType() {

    }

    public AccountType (String name) {
        super(name);
    }


    public Set<PositionType> getPositionTypes() {
        return positionTypes;
    }

    public Set<TransactionType> getTransactionTypes() {
        return transactionTypes;
    }

    public Optional<TransactionType> getTransactionType(Long transactionTypeId){

        for(TransactionType transactionType : getTransactionTypes()) {
           if(transactionType.getId()== transactionTypeId){
               return Optional.of(transactionType);
           }
        }

        return Optional.empty();
    }


}
