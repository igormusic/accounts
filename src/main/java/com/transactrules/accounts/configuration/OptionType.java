package com.transactrules.accounts.configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.transactrules.accounts.NamedAbstractEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class OptionType extends NamedAbstractEntity {

    @JsonIgnore
    @ManyToOne
    private AccountType accountType;

    @Column(length = 64000)
    private String optionListExpression;

    public OptionType(){

    }

    public OptionType(AccountType accountType, String name, String optionListExpression) {
        super(name);
        this.accountType = accountType;
        this.optionListExpression = optionListExpression;
    }

    public AccountType accountType() {
        return accountType;
    }

    public String optionListExpression(){
        return this.optionListExpression;
    }
}
