package com.transactrules.accounts.runtime;

import com.transactrules.accounts.configuration.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by 313798977 on 2016/11/12.
 */


@Service
public class AccountValuationServiceImpl implements AccountValuationService {

    private AccountTypeRepository accountTypeRepository;
    private Account account;
    private AccountType accountType;
    private Map<Long, Position> positionMap;
    private LocalDate actionDate;
    private LocalDate valueDate;

    @Autowired
    public AccountValuationServiceImpl(AccountTypeRepository accountTypeRepository){
        this.accountTypeRepository = accountTypeRepository;
    }

    @Override
    public void initialize(Account account) {
        this.account = account;
        positionMap = new HashMap<>();
        accountType = accountTypeRepository.findOne(account.getAccountTypeId());
        actionDate = LocalDate.now();
        valueDate = LocalDate.now();


        initializePositions(account);

    }

    private void initializePositions(Account account) {
        for (PositionType positionType: accountType.getPositionTypes()) {

            boolean hasPosition = false;

            for(Position position: account.getPositions()) {
                if(position.getPositionTypeId()== positionType.getId()) {
                    positionMap.put(positionType.getId(), position);
                    hasPosition = true;
                }
            }

            if(hasPosition==false){
                Position newPosition = new Position(positionType.getId(),account);
                account.getPositions().add(newPosition);
                positionMap.put(positionType.getId(), newPosition);
            }
        }
    }

    @Override
    public void processTransaction(Transaction transaction) throws Exception {
        Optional<TransactionType> transactionType
                = accountType.getTransactionType(transaction.getTransactionTypeId());

        if(transactionType.isPresent()==false){
            throw new Exception(
                    String.format(
                            "TrasnactionType with Id %d not defined for this AccountType",
                            transaction.getTransactionTypeId()
                    )
            );
        }

        for (TransactionRuleType rule: transactionType.get().getTransactionRules()) {
            Position position = positionMap.get(rule.getPositionTypeId());

            position.applyOperation(rule.getTransactionOperation(), transaction.getAmount());
        }

    }

    @Override
    public Transaction CreateTransaction(TransactionType transactionType, BigDecimal amount) {
//        var transaction = new Transaction { TransactionType = transactionType, Amount = amount };
//        ProcessTransaction(transaction);
//        Transactions.Add(transaction);
//
//        return transaction;

        Transaction transaction = new Transaction(transactionType.getId(),amount,account, actionDate, valueDate);

        processTransaction(transaction);

        return transaction;
    }
}
