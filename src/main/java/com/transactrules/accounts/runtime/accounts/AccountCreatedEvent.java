package com.transactrules.accounts.runtime.accounts;

//import org.axonframework.eventsourcing.GenericDomainEventMessage;
//extends GenericDomainEventMessage<Account>
public class AccountCreatedEvent {
    public final String accountId;
    public final String accountNumber;
    public final Long accountTypeId;

    public AccountCreatedEvent(String accountId, String accountNumber, Long accountTypeId) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.accountTypeId = accountTypeId;
    }
}

