package com.transactrules.accounts.runtime.accounts;

import com.transactrules.accounts.AbstractEntity;
import com.transactrules.accounts.configuration.PositionType;
import com.transactrules.accounts.runtime.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateLifecycle;
//import org.axonframework.spring.stereotype.Aggregate;
import org.axonframework.common.IdentifierFactory;
import org.axonframework.eventsourcing.EventSourcingHandler;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.*;

/**
 * Created by 313798977 on 2016/11/11.
 */
@Entity
public class Account {

    @Id
    private String accountId;
    private String accountNumber;
    private boolean isActive;
    private Long accountTypeId;
    public transient BusinessDayCalculator businessDayCalculator;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Map<String,Position> positions = new HashMap<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Map<String,DateValue> dates = new HashMap<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Set<Transaction> transactions = new HashSet<>();

    public Account() {

    }

    @CommandHandler
    public Account(CreateAccountCommand createAccountCmd){
/*
        AggregateLifecycle.apply(
                new AccountCreatedEvent(
                        IdentifierFactory.getInstance().generateIdentifier(),
                        createAccountCmd.accountNumber,
                        createAccountCmd.accountTypeId));
                        */

handleAccountCreated( new AccountCreatedEvent(
        IdentifierFactory.getInstance().generateIdentifier(),
        createAccountCmd.accountNumber,
        createAccountCmd.accountTypeId));
    }

    @CommandHandler
    public void createTransaction(CreateTransactionCommand command){

    }

    @EventSourcingHandler
    private void handleAccountCreated(AccountCreatedEvent event){
        this.accountId = event.accountId;
        this.accountNumber = event.accountNumber;
        this.accountTypeId = event.accountTypeId;
    }

    public String accountNumber() {
        return accountNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public Long accountTypeId() {
        return accountTypeId;
    }

    public Map<String,Position> positions() {

        return Collections.unmodifiableMap(this.positions);
    }

    public Map<String,DateValue> dates(){
        return this.dates;
    }

    public Set<Transaction> transactions() {

        return Collections.unmodifiableSet(transactions);
    }



    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public Position initializePosition(PositionType positionType) {

        Position position = new Position(positionType.id(), this);
        positions.put(positionType.name(), position);
        return position;
    }

}
