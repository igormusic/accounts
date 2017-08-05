package com.transactrules.accounts.runtime;

import com.transactrules.accounts.AbstractEntity;
import com.transactrules.accounts.configuration.BusinessDayCalculation;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
public class Calendar extends AbstractEntity implements BusinessDayCalculator {
    transient private Map<LocalDate, HolidayDate> holidaysMap;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "calendar")
    private Set<HolidayDate> holidays = new HashSet<>();

    public Boolean IsBusinessDay(LocalDate date)
    {
        if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY )
        {
            return false;
        }

        return holidaysMap().containsKey(date);
    }

    protected Map<LocalDate, HolidayDate> holidaysMap()
    {

        if (holidaysMap== null)
        {
            holidaysMap = holidays.stream().collect(Collectors.toMap(HolidayDate::value, Function.identity())) ;
        }
        
        return holidaysMap;
    }

    public LocalDate GetCalculatedBusinessDay(LocalDate date, BusinessDayCalculation adjsutment)
    {
        if (adjsutment == BusinessDayCalculation.AnyDay)
        {
            return date;
        }

        if (adjsutment == BusinessDayCalculation.PreviousBusinessDay)
        {
            return GetPreviousBusinessDay(date);
        }

        if (adjsutment == BusinessDayCalculation.NextBusinessDay)
        {
            return GetNextBusinessDay(date);
        }

        LocalDate previousBusinessDay = GetPreviousBusinessDay(date);
        LocalDate nextBusinessDay = GetPreviousBusinessDay(date);

        if (adjsutment == BusinessDayCalculation.ClosestBusinessDayOrNext)
        {
            if (Period.between(date,previousBusinessDay).getDays() > Period.between(nextBusinessDay,date).getDays())
            {
                return nextBusinessDay;
            }
            else if (Period.between(date,previousBusinessDay).getDays() < Period.between( nextBusinessDay,date).getDays())
            {
                return previousBusinessDay;
            }
            else {
                return nextBusinessDay;
            }
        }

        //last option is NextBusinessDayThisMonthOrPrevious

        if (nextBusinessDay.getMonth() == date.getMonth())
        {
            return nextBusinessDay;
        }

        return previousBusinessDay;

    }

    public LocalDate GetPreviousBusinessDay(LocalDate date)
    {
        while (!IsBusinessDay(date))
        {
            date = date.plusDays(-1);
        }

        return date;
    }

    public  LocalDate GetNextBusinessDay(LocalDate date)
    {
        while (!IsBusinessDay(date))
        {
            date = date.plusDays(1);
        }

        return date;
    }
}




