package com.transactrules.accounts.runtime;

import com.transactrules.accounts.configuration.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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
        accountType = accountTypeRepository.findOne(account.accountTypeId());
        actionDate = LocalDate.now();
        valueDate = LocalDate.now();


        initializePositions(account);

    }

    private void initializePositions(Account account) {
        for (PositionType positionType: accountType.positionTypes()) {

            boolean hasPosition = false;

            for(Position position: account.positions()) {
                if(position.getPositionTypeId()== positionType.id()) {
                    positionMap.put(positionType.id(), position);
                    hasPosition = true;
                }
            }

            if(hasPosition==false){

                Position newPosition = account.initializePosition(positionType);

                positionMap.put(positionType.id(), newPosition);
            }
        }
    }

    private void processTransaction(TransactionType transactionType, BigDecimal amount) {

        for (TransactionRuleType rule: transactionType.getTransactionRules()) {
            Position position = positionMap.get(rule.positionTypeId());

            position.applyOperation(rule.transactionOperation(), amount);
        }
    }

    @Override
    public Transaction createTransaction(TransactionType transactionType, BigDecimal amount) {

        Transaction transaction = new Transaction(transactionType.id(),amount,account, actionDate, valueDate);

        processTransaction(transactionType, amount);

        account.addTransaction(transaction);

        return transaction;
    }
}
