package com.transactrules.accounts.configuration;


import com.transactrules.accounts.NamedAbstractEntity;

import javax.persistence.*;
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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "accountType")
    private Set<DateType> dateTypes = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "accountType")
    private Set<AmountType> amountTypes = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "accountType")
    private Set<RateType> rateTypes = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "accountType")
    private Set<OptionType> optionTypes = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "accountType")
    private Set<ScheduledTransaction> scheduledTransactions = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "accountType")
    private Set<TransactionType> transactionTypes = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "accountType")
    private Set<ScheduleType> scheduleTypes = new HashSet<>();

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

    public Set<DateType> dateTypes(){
        return Collections.unmodifiableSet(dateTypes);
    }

    public Set<AmountType> amountTypes(){
        return Collections.unmodifiableSet(amountTypes);
    }

    public Set<OptionType> optionTypes(){
        return Collections.unmodifiableSet(optionTypes);
    }

    public Optional<TransactionType> getTransactionType(Long transactionTypeId){

        for(TransactionType transactionType : transactionTypes()) {
           if(transactionType.id()== transactionTypeId){
               return Optional.of(transactionType);
           }
        }

        return Optional.empty();
    }

    public DateType addDateType(String name){
        DateType dateType = new DateType(this,name);
        dateTypes.add(dateType);
        return dateType;
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

    public TransactionType addTransactionType(String name){
        TransactionType transactionType = new TransactionType(this,name, false);
        transactionTypes.add(transactionType);

        return transactionType;
    }

    public ScheduleType addCalculatedScheduleType(String name,
                                                  ScheduleFrequency frequency,
                                                  ScheduleEndType endType,
                                                  String startDateExpression,
                                                  String endDateExpression,
                                                  String numberOfRepeatsExpression,
                                                  String intervalExpression){

        ScheduleType scheduleType = new ScheduleType(this, name, frequency,endType, startDateExpression, endDateExpression,numberOfRepeatsExpression,intervalExpression,true);

        scheduleTypes.add(scheduleType);

        return  scheduleType;
    }

    public ScheduleType addUserInputScheduleType(String name,
                                                 ScheduleFrequency frequency,
                                                 ScheduleEndType endType,
                                                 String startDateExpression,
                                                 String endDateExpression,
                                                 String numberOfRepeatsExpression,
                                                 String intervalExpression){

        ScheduleType scheduleType = new ScheduleType(this, name, frequency,endType, startDateExpression, endDateExpression,numberOfRepeatsExpression,intervalExpression,false);

        scheduleTypes.add(scheduleType);

        return  scheduleType;
    }

    public Optional<PositionType> getPositionTypeByName(String name){
        return positionTypes.stream().filter(pt -> pt.name().equalsIgnoreCase(name)).findFirst();
    }

    public Optional<TransactionType> getTransactionTypeByName(String name){
        return transactionTypes.stream().filter(tt->tt.name().equalsIgnoreCase(name)).findFirst();
    }


    public AmountType addAmountType(String name, Boolean isValueDated) {
        AmountType amountType = new AmountType(this,name, isValueDated);
        amountTypes.add(amountType);
        return amountType;
    }

    public RateType addRateType(String name){
        RateType rateType = new RateType(this, name);
        rateTypes.add(rateType);
        return rateType;
    }

    public OptionType addOptionType(String name, String optionListExpression){
        OptionType optionType = new OptionType(this, name, optionListExpression);
        optionTypes.add(optionType);
        return  optionType;
    }

    public ScheduledTransaction addDayScheduledTransaction(String name,ScheduledTransactionTiming timing,DateType dateType, TransactionType transactionType, String amountExpression, Integer sequence){
        ScheduledTransaction scheduledTransaction = new ScheduledTransaction( this,name,timing,dateType, null, transactionType, amountExpression, sequence);

        scheduledTransactions.add(scheduledTransaction);

        return  scheduledTransaction;
    }

    public ScheduledTransaction addScheduledTransaction(String name,ScheduledTransactionTiming timing,ScheduleType scheduleType, TransactionType transactionType, String amountExpression, Integer sequence){
        ScheduledTransaction scheduledTransaction = new ScheduledTransaction( this,name,timing,null, scheduleType, transactionType, amountExpression, sequence);

        scheduledTransactions.add(scheduledTransaction);

        return  scheduledTransaction;
    }
}
