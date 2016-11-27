package com.transactrules.accounts;

import com.transactrules.accounts.configuration.AccountType;
import com.transactrules.accounts.configuration.AccountTypeRepository;
import com.transactrules.accounts.configuration.PositionType;
import com.transactrules.accounts.configuration.TransactionType;
import com.transactrules.accounts.runtime.Account;
import com.transactrules.accounts.runtime.AccountValuationService;
import com.transactrules.accounts.runtime.Position;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Created by Administrator on 11/26/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Autowired
    private AccountValuationService accountValuationService;

    private AccountType savingsAccountType = AccountTypeFactory.CreateSavingsAccountType();

    @Before
    public void initialize()
    {
        accountTypeRepository.save(savingsAccountType);
    }

    @Test
    public void ProcessTransaction_deposit(){

        Account account = new Account("001", savingsAccountType.getId());

        Optional<TransactionType> depositTransactionType = savingsAccountType.getTransactionTypeByName("Deposit");
        Optional<PositionType> currentPositionType = savingsAccountType.getPositionTypeByName("Current");

        assertThat(depositTransactionType.isPresent(), is(true));
        assertThat(currentPositionType.isPresent(), is(true));

        accountValuationService.initialize(account);

        accountValuationService.createTransaction(depositTransactionType.get(), BigDecimal.valueOf(100));

        Optional<Position> currentPosition = account.getPositions().stream().filter(p -> p.getPositionTypeId() == currentPositionType.get().getId()).findFirst();

        assertThat(currentPosition.isPresent(),is(true));

        assertThat(currentPosition.get().getAmount(), is(BigDecimal.valueOf(100)));
    }
}