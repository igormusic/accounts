package com.transactrules.accounts.runtime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.transactrules.accounts.AbstractEntity;
import com.transactrules.accounts.runtime.accounts.Account;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class DateValue extends AbstractEntity {
    private LocalDate date;

    @JsonIgnore
    @ManyToOne
    private Account account;

    DateValue(){

    }

    public DateValue(Account account, LocalDate date) {
        this.date = date;
        this.account = account;
    }

    public LocalDate date() {
        return date;
    }

    public Account account() {
        return account;
    }

}
