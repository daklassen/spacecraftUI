package de.david.worksample.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {

    private static final String DATE_PATTERN = "dd.MM.yyyy";

    public static String format(Date date) {
        SimpleDateFormat dt1 = new SimpleDateFormat(DATE_PATTERN);
        String formatedDate = dt1.format(date);
        return formatedDate;
    }

    public static Date localDateToDate (LocalDate localDate) {
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date res = Date.from(instant);
        return res;
    }

    public static LocalDate dateTolocalDate (Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }
}