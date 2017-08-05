package com.transactrules.accounts.configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.transactrules.accounts.NamedAbstractEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

/**
 * Created by Administrator on 11/27/2016.
 */
@Entity
public class ScheduleType extends NamedAbstractEntity {
    @JsonIgnore
    @ManyToOne
    private AccountType accountType;

    @Enumerated(EnumType.STRING)
    private ScheduleFrequency scheduleFrequency;

    @Enumerated(EnumType.STRING)
    private ScheduleEndType scheduleEndType;

    private String startDateExpression;
    private String endDateExpression;
    private String numberOfRepeatsExpression;
    private String intervalExpression;
    private Boolean isCalculated;

    ScheduleType(){}

    ScheduleType(
            AccountType accountType,
            String name,
            ScheduleFrequency frequency,
            ScheduleEndType endType,
            String startDateExpression,
            String endDateExpression,
            String numberOfRepeatsExpression,
            String intervalExpression,
            Boolean isCalculated) {
        super(name);
        this.accountType = accountType;
        this.scheduleFrequency = frequency;
        this.scheduleEndType = endType;
        this.startDateExpression = startDateExpression;
        this.endDateExpression = endDateExpression;
        this.numberOfRepeatsExpression = numberOfRepeatsExpression;
        this.intervalExpression = intervalExpression;
        this.isCalculated = isCalculated;
    }

    public AccountType accountType() {
        return accountType;
    }

    public String startDateExpression() {
        return startDateExpression;
    }

    public String endDateExpression() {
        return endDateExpression;
    }

    public String numberOfRepeatsExpression() {
        return numberOfRepeatsExpression;
    }

    public String intervalExpression() {
        return intervalExpression;
    }

    public Boolean isCalculated() {
        return isCalculated;
    }
}
