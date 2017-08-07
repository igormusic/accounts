package com.transactrules.accounts.runtime;

import com.transactrules.accounts.runtime.accounts.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 313798977 on 2016/11/12.
 */
public interface AccountRepository extends JpaRepository<Account,Long> {
}
