package com.transactrules.accounts.runtime;

import com.transactrules.accounts.configuration.TransactionType;

import java.math.BigDecimal;

/**
 * Created by 313798977 on 2016/11/12.
 */
public interface AccountValuationService {

    void initialize(Account account);

    void processTransaction(Transaction transaction) throws Exception;

    Transaction CreateTransaction(TransactionType transactionType, BigDecimal amount);

}
