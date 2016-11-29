package com.transactrules.accounts.configuration;

import com.transactrules.accounts.AbstractEntity;

import java.time.LocalDate;

/**
 * Created by Administrator on 11/27/2016.
 */
public abstract class DateExpression extends AbstractEntity {
    abstract LocalDate getValue();


}
