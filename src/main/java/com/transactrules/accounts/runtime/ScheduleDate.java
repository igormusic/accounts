package com.transactrules.accounts.runtime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.transactrules.accounts.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class ScheduleDate extends AbstractEntity
{
    @JsonIgnore
    @ManyToOne
    private Schedule includeInSchedule;

    @JsonIgnore
    @ManyToOne
    private Schedule excludeFromSchedule;

    private LocalDate value ;

    public LocalDate value(){
        return  value;
    }
}