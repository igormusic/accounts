package com.transactrules.accounts.runtime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.transactrules.accounts.AbstractEntity;
import org.apache.tomcat.jni.Local;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class ScheduleDate extends AbstractEntity
{
    public ScheduleDate(){}

    public ScheduleDate(LocalDate value){
        this.value = value;
    }

    @JsonIgnore
    @ManyToOne
    private Schedule includeInSchedule;

    @JsonIgnore
    @ManyToOne
    private Schedule excludeFromSchedule;

    public LocalDate value ;

}