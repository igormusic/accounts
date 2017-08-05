package com.transactrules.accounts.runtime;

import com.transactrules.accounts.AbstractEntity;
import com.transactrules.accounts.configuration.PositionType;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.*;

/**
 * Created by 313798977 on 2016/11/11.
 */
@Entity
public class Account extends AbstractEntity {

    private String accountNumber;
    private boolean isActive;
    private Long accountTypeId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Map<String,Position> positions = new HashMap<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Map<String,DateValue> dates = new HashMap<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Set<Transaction> transactions = new HashSet<>();

    Account() {

    }

    public Account(String accountNumber, Long accountTypeId) {
        this.accountNumber = accountNumber;
        this.accountTypeId = accountTypeId;
        this.isActive = false;
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
