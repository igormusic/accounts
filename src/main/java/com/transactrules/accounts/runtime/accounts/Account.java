package com.transactrules.accounts.runtime.accounts;

import com.transactrules.accounts.configuration.*;
import com.transactrules.accounts.runtime.*;
import org.axonframework.commandhandling.CommandHandler;
//import org.axonframework.spring.stereotype.Aggregate;
import org.axonframework.common.IdentifierFactory;
import org.axonframework.eventsourcing.EventSourcingHandler;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by 313798977 on 2016/11/11.
 */
@Entity
public class Account {

    @Id
    private String id;
    private String accountNumber;
    private boolean isActive;
    private Long accountTypeId;
    public transient BusinessDayCalculator businessDayCalculator;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Map<Long,Position> positions = new HashMap<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Map<Long,DateValue> dates = new HashMap<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Map<Long,AmountValue> amounts = new HashMap<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Map<Long,OptionValue> options = new HashMap<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Map<Long,Schedule> schedules = new HashMap<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Set<Transaction> transactions = new HashSet<>();

    public Account() {

    }

    public void initialize(AccountType accountType){
        for(PositionType positionType: accountType.positionTypes()){
            if(!positions.containsKey(positionType.id())){
                initializePosition(positionType);
            }
        }

        for (DateType dateType: accountType.dateTypes()) {
            if(!dates.containsKey(dateType.id())){
                dates.put(dateType.id(), new DateValue(this, LocalDate.MIN));
            }
        }

        for (AmountType amountType: accountType.amountTypes()) {
            if(!amounts.containsKey(amountType.id())){
                amounts.put(amountType.id(), new AmountValue(this, new BigDecimal(0)));
            }
        }

        for (OptionType optionType: accountType.optionTypes()){
            if(!options.containsKey(optionType.id())){
                options.put(optionType.id(), new OptionValue(this));
            }
        }
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
        this.id = event.accountId;
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

    public Map<Long,Position> positions() {
        return Collections.unmodifiableMap(this.positions);
    }

    public Map<Long,DateValue> dates(){
        return Collections.unmodifiableMap( this.dates);
    }

    public Map<Long, AmountValue> amounts() {
        return Collections.unmodifiableMap(this.amounts);
    }

    public Map<Long,OptionValue> options() {
        return Collections.unmodifiableMap(this.options);
    }

    public Set<Transaction> transactions() {
        return Collections.unmodifiableSet(transactions);
    }

    public Map<Long,Schedule> schedules(){
        return  Collections.unmodifiableMap(schedules);
    }


    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public DateValue initializeDate(DateType dateType, LocalDate date){
        DateValue dateValue;


        if(!dates.containsKey(dateType.name())){
            dateValue = new DateValue(this, date);
            dates.put(dateType.id(), dateValue);
        }
        else{
            dateValue = dates.get(dateType.name());
        }

        return dateValue;
    }

    public Position initializePosition(PositionType positionType) {
        Position position = new Position(positionType.id(), this);
        positions.put(positionType.id(), position);
        return position;
    }

    public Schedule initializeSchedule(ScheduleType scheduleType){
        Schedule schedule = new Schedule();

        schedules.put(scheduleType.id(), schedule);

        return schedule;
    }

}
