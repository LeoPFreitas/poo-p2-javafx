package org.example.domain.utils;

import java.time.LocalDate;

public class DateRange extends Range<LocalDate> {
    private final LocalDate start;
    private final LocalDate end;

    public DateRange(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public boolean includes(LocalDate arg) {
        return !arg.isBefore(start) && !arg.isAfter(end);
    }
}
