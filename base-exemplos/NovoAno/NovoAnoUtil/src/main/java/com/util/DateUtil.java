package com.util;

import org.joda.time.Days;
import org.joda.time.LocalDate;

public class DateUtil {
	public int daysToNewYear() {
		LocalDate fromDate = new LocalDate();
		LocalDate newYear = fromDate.plusYears(1).withDayOfYear(1);
		return Days.daysBetween(fromDate, newYear).getDays();
	}

}
