package com.corebyte.mob.kiipa.util;

import android.os.ParcelUuid;
import android.util.Log;

import com.corebyte.mob.kiipa.model.BaseModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    private static final String DATE_FORMAT_1 = "EEE, dd MMM yyyy HH:mm:ss aaa";
    private static final String DATE_FORMAT_2 = "dd/MM/yyyy";
    public static final String DATE_FORMAT_3 = "yyyy-MM-dd HH:mm:ss";
    public static final SimpleDateFormat DB_DATE_STR_FORMAT = new SimpleDateFormat(DATE_FORMAT_3, Locale.getDefault());
    private static SimpleDateFormat dateFormat =
            new SimpleDateFormat(DATE_FORMAT_1, Locale.getDefault());

    public static String getDateFormat(Date date) {
        return date == null ? null : dateFormat.format(date);
    }

    public static String getDateFormat2(Date date) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_2, Locale.getDefault());
        return date == null ? null : simpleDateFormat.format(date);
    }

    public static String getDateString(Date date) {

        if (date == null) return null;

        try {
            return DB_DATE_STR_FORMAT.format(date);
        }catch (Exception e) {
            Log.i(DateConverter.class.getSimpleName(), "Error parsing date: "+ e.getMessage());
            return null;
        }
    }

    public static Date getStringDate(String date) {

        if (date == null) return null;

        try {
            String[] dates = date.split(" ");
            String[] datestrs = dates[0].split("-");
            String[] timestr = dates[1].split(":");

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.valueOf(datestrs[0]));
            calendar.set(Calendar.MONTH, Integer.valueOf(datestrs[1]));
            calendar.set(Calendar.DATE, Integer.valueOf(datestrs[2]));

            calendar.set(Calendar.HOUR, Integer.valueOf(timestr[0]));
            calendar.set(Calendar.MINUTE, Integer.valueOf(timestr[1]));
            calendar.set(Calendar.SECOND, Integer.valueOf(timestr[2]));
            return calendar.getTime();

        }catch (Exception e) {
            Log.i(DateConverter.class.getSimpleName(), "Error parsing string: "+ e.getMessage());
            return null;
        }
    }

    public static void initCreateDate(BaseModel... models) {
        for (BaseModel model : models) {
            model.initDates();
        }
    }

    public static void modifyUpdateDate(BaseModel... models) {
        for (BaseModel model : models) {
            model.updatedAt = new Date();
        }
    }

}
