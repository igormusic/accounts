package com.transactrules.accounts.configuration;


import com.transactrules.accounts.NamedAbstractEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Aggregate root for account configuration
 */

@Entity
public class AccountType extends NamedAbstractEntity {

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "accountType")
    private Set<PositionType> positionTypes = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "accountType")
    private Set<TransactionType> transactionTypes = new HashSet<>();

    AccountType() {

    }

    public AccountType (String name) {
        super(name);
    }


    public Set<PositionType> positionTypes() {

        return Collections.unmodifiableSet(positionTypes);
    }

    public Set<TransactionType> transactionTypes() {

        return Collections.unmodifiableSet(transactionTypes);
    }

    public Optional<TransactionType> getTransactionType(Long transactionTypeId){

        for(TransactionType transactionType : transactionTypes()) {
           if(transactionType.id()== transactionTypeId){
               return Optional.of(transactionType);
           }
        }

        return Optional.empty();
    }

    public PositionType addPositionType(String name){
        PositionType positionType = new PositionType(this, name);
        positionTypes.add(positionType);
        return positionType;
    }

    public TransactionType addTransactionType(String name, boolean hasMaximumPrecission){
        TransactionType transactionType = new TransactionType(this,name, hasMaximumPrecission);
        transactionTypes.add(transactionType);

        return transactionType;
    }

    public Optional<PositionType> getPositionTypeByName(String name){
        return positionTypes.stream().filter(pt -> pt.name().equalsIgnoreCase(name)).findFirst();
    }

    public Optional<TransactionType> getTransactionTypeByName(String name){
        return transactionTypes.stream().filter(tt->tt.name().equalsIgnoreCase(name)).findFirst();
    }



}
