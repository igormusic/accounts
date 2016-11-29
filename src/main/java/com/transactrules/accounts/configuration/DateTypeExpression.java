package com.transactrules.accounts.configuration;


import javax.persistence.ManyToOne;
import java.time.LocalDate;

/**
 * Created by Administrator on 11/27/2016.
 */
public class DateTypeExpression extends DateExpression {
    @ManyToOne
    private DateType dateType;

    @Override
    LocalDate getValue() {
        return null;
    }
}
