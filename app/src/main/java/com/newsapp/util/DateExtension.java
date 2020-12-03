package com.newsapp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Lokesh Mudgal on 2/12/20.
 */
public class DateExtension {
    private static final SimpleDateFormat paramFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSSSS'Z'", Locale.US);

    public static String dateToShow(String date) {
        return paramFormat.format(getDateToShow(date));
    }

    private static Date getDateToShow(String date) {
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return Calendar.getInstance().getTime();
        }
    }
}
