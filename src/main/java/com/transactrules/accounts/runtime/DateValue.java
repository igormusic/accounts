package com.transactrules.accounts.runtime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.transactrules.accounts.AbstractEntity;
import com.transactrules.accounts.runtime.accounts.Account;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class DateValue extends AbstractEntity {


    @JsonIgnore
    @ManyToOne
    public Account account;
    private LocalDate date;

    DateValue(){

    }

    public DateValue(Account account, LocalDate date) {
        this.date = date;
        this.account = account;
    }

    public LocalDate date(){
        return date;
    }

}
