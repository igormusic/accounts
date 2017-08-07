package com.transactrules.accounts.runtime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.transactrules.accounts.AbstractEntity;
import com.transactrules.accounts.configuration.BusinessDayCalculation;
import com.transactrules.accounts.configuration.ScheduleEndType;
import com.transactrules.accounts.configuration.ScheduleFrequency;
import org.apache.tomcat.jni.Local;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Schedule extends AbstractEntity {
    public Schedule() {
        includeDates = new HashSet<>();
        excludeDates = new HashSet<>();
    }

    @JsonIgnore
    @ManyToOne
    public Account account;

    public String scheduleType;
    public LocalDate startDate;
    public ScheduleEndType endType;
    public ScheduleFrequency frequency;
    public LocalDate endDate;
    public Integer interval;
    public Integer numberOfRepeats;

    public BusinessDayCalculation businessDayCalculation;
    
    public transient BusinessDayCalculator businessDayCalculator;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "includeInSchedule")
    public Set<ScheduleDate> includeDates;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "excludeFromSchedule")
    public Set<ScheduleDate> excludeDates;
    
    public Boolean IsDue(LocalDate date)
    {
        if (IsSimpleDailySchedule())
        {
            if (endType == ScheduleEndType.NoEnd)
            {
                return date.isBefore(startDate);
            }
            else if (endType == ScheduleEndType.EndDate)
            {
                return (date.isEqual(startDate) || date.isAfter(startDate)) &&
                        ( date.isEqual(endDate) || date.isBefore(endDate));
            }
        }
        
        List<LocalDate> dates = GetAllDates(LastPossibleDate());

        return dates.contains(date);
    }

    //get next fifty years of dates assuming no schedule will be longer than that
    private LocalDate LastPossibleDate()
    {
        return startDate.plusYears(50);
    }

    private Boolean IsSimpleDailySchedule()
    {
        return this.frequency == ScheduleFrequency.Daily && this.interval == 1 && this.businessDayCalculation == BusinessDayCalculation.AnyDay;
    }

    public Boolean IsDue()
    {
        return IsDue(SessionState.current().valueDate());
    }

    private transient Map<LocalDate, List<LocalDate>> cachedDates = new HashMap<>();

    public List<LocalDate> GetAllDates()
    {
        return GetAllDates(LastPossibleDate());
    }

    public List<LocalDate> GetAllDates(LocalDate toDate)
    {
        if (cachedDates.containsKey(toDate))
            return cachedDates.get(toDate);

        Set<LocalDate> dates = new HashSet<>();

        int repeats = 1;
        LocalDate iterator = startDate;
        LocalDate adjustedDate = businessDayCalculator.GetCalculatedBusinessDay(iterator, this.businessDayCalculation);

        while (!IsCompleted(repeats, adjustedDate, toDate))
        {
            dates.add(adjustedDate);

            iterator = GetNextDate(repeats, iterator);

            adjustedDate = businessDayCalculator.GetCalculatedBusinessDay(iterator, this.businessDayCalculation);

            repeats++;
        }

        for(ScheduleDate includeDate: this.includeDates){
            dates.add( includeDate.value);
        }


        for(ScheduleDate excludeDate : this.excludeDates)
        {
            dates.remove(excludeDate.value);
        }

        List<LocalDate> sortedDates = dates.stream().sorted().collect(Collectors.toList());
        cachedDates.put(toDate,sortedDates );

        return sortedDates;
    }

    private LocalDate GetNextDate(int repeats, LocalDate iterator)
    {
        if (this.frequency == ScheduleFrequency.Daily)
        {
            iterator = startDate.plusDays(interval * repeats);
        }
        else
        {
            iterator = startDate.plusMonths(interval * repeats);
        }

        return iterator;
    }

    private Boolean IsCompleted(Integer repeats, LocalDate lastDate, LocalDate endDate)
    {
        if (lastDate.isAfter(endDate))
        {
            return true;
        }

        if (this.endType == ScheduleEndType.EndDate && lastDate.isAfter(endDate))
        {
            return true;
        }


        if (this.endType == ScheduleEndType.NoEnd)
        {
            return false;
        }

        if (this.endType == ScheduleEndType.Repeats &&
                repeats > this.numberOfRepeats)
        {
            return true;
        }

        return lastDate.isAfter(this.endDate);

    }
}
