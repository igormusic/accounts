package com.transactrules.accounts.configuration;


import com.transactrules.accounts.NamedAbstractEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Aggregate root for account configuration
 */

@Entity
public class AccountType extends NamedAbstractEntity {

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<PositionType> positionTypes = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
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
        return positionTypes.stream().filter(pt -> pt.getName().equalsIgnoreCase(name)).findFirst();
    }

    public Optional<TransactionType> getTransactionTypeByName(String name){
        return transactionTypes.stream().filter(tt->tt.getName().equalsIgnoreCase(name)).findFirst();
    }



}
