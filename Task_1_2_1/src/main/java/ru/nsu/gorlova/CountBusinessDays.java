package ru.nsu.gorlova;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class CountBusinessDays {
    public int countBusinessDays(LocalDate start, LocalDate end, List<LocalDate> holidays) {
        LocalDate date = start;
        int count = 0;
        while (date.isBefore(end)) {
            if (date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY && !holidays.contains(date)) {
                count++;
            }
            date = date.plusDays(1);
        }
        return count;
    }

}
