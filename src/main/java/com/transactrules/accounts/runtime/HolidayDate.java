package com.transactrules.accounts.runtime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.transactrules.accounts.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class HolidayDate extends AbstractEntity {

    @JsonIgnore
    @ManyToOne
    private Calendar calendar;

    private String description;
    private LocalDate value;

    public LocalDate value(){
        return value;
    }

    public HolidayDate(){

    }

    public HolidayDate(String description, LocalDate value){
        this.description = description;
        this.value = value;
    }
}
