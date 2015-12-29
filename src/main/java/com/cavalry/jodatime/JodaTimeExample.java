package com.cavalry.jodatime;

import org.joda.time.DateTime;

import java.util.Date;
import java.util.Locale;

/**
 * author: master
 * date: 2015/12/29
 */
public class JodaTimeExample {

    // http://www.joda.org/joda-time/quickstart.html
    public static void main(String[] args) {
        java.util.Date juDate = new Date();
        DateTime dt = new DateTime(juDate);

        int month = dt.getMonthOfYear();  // where January is 1 and December is 12
        int year = dt.getYear();

        DateTime year2000 = dt.withYear(2000);
        DateTime twoHoursLater = dt.plusHours(2);

        String monthName = dt.monthOfYear().getAsText();
        String frenchShortName = dt.monthOfYear().getAsShortText(Locale.FRENCH);
        boolean isLeapYear = dt.year().isLeap();
        DateTime rounded = dt.dayOfMonth().roundFloorCopy();
    }
}
