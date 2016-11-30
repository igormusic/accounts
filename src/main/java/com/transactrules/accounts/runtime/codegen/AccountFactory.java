package com.transactrules.accounts.runtime.codegen;


import com.transactrules.accounts.configuration.AccountType;
import com.transactrules.accounts.runtime.Account;
import com.transactrules.accounts.runtime.TransactionClient;

import java.util.HashMap;
import java.util.Map;

public class AccountFactory {
    private static Map<String, Class> types = new HashMap<String, Class>();

    public static TransactionClient CreateTransactionClient(AccountType accountType, Account account) throws IllegalAccessException, InstantiationException {
        Class generatedType = GetGeneratedAccountType(accountType);

        TransactionClient client;

        client = (TransactionClient) generatedType.newInstance();

        client.initialize(account);

        return client;
    }

    private static Class GetGeneratedAccountType(AccountType accountType) {
        Class type;

        String typeName = GetTypeName(accountType);

        synchronized (types) {
            if (types.containsKey(typeName) == false) {
                type = GenerateAccountType(accountType);
                types.put(typeName, type);
            } else {
                type =  types.get(typeName);
            }
        }

        return type;
    }

    private static String GetTypeName(AccountType accountType) {
        return "com.transactrules.accounts.runtime" + accountType.getName();
    }

    private static Class GenerateAccountType(AccountType accountType) {
        return null;
    }
}