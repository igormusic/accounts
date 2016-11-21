package com.transactrules.accounts.runtime;

import com.transactrules.accounts.AbstractEntity;
import com.transactrules.accounts.configuration.AccountType;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 313798977 on 2016/11/11.
 */
@Entity
public class Account extends AbstractEntity {

    private String accountNumber;
    private boolean isActive;
    private Long accountTypeId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Set<Position> positions = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Set<Transaction> transactions;

    Account(){

    }
    public Account(String accountNumber, Long accountTypeId) {
        this.accountNumber = accountNumber;
        this.accountTypeId = accountTypeId;
        this.isActive = false;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public Long getAccountTypeId() {
        return accountTypeId;
    }

    public Set<Position> getPositions() {
        return positions;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

}
