package com.transactrules.accounts.runtime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.transactrules.accounts.AbstractEntity;
import com.transactrules.accounts.runtime.accounts.Account;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class OptionValue extends AbstractEntity{
    private String value;

    @Transient
    private Iterable<String> values;

    @JsonIgnore
    @ManyToOne
    public Account account;

    OptionValue(){

    }

    public OptionValue(Account account) {
        this.account = account;
    }

    public String value(){
        return value;
    }

    public Iterable<String> getValues() {
        return values;
    }

    public void setValues(Iterable<String> list){
        values = list;
    }
}
