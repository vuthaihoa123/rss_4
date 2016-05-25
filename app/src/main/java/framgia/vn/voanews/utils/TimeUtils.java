package framgia.vn.voanews.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {
    public static final String DATE_INPUT = "EEE, dd MMM yyyy k:m:s ZZZ";
    public static final String DATE_OUTPUT = "k:m:s dd-MMM-yyyy";
    public static String toStringDate(long milisec) {
        String dateString = new SimpleDateFormat(DATE_OUTPUT).format(new Date(milisec));
        return dateString;
    }

    public static long toTime(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        try {
            Date date = dateFormat.parse(dateString);
            return date.getTime();
        } catch (ParseException e) {
            return 0;
        }
    }

    public static Date stringToDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_INPUT, Locale.US);
        Date date = new Date();
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    public static String toStringDate(Date date) {
        return new SimpleDateFormat(DATE_OUTPUT).format(date);
    }
}
