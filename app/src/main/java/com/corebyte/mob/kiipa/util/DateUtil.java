package com.corebyte.mob.kiipa.util;

import android.util.Log;

import com.corebyte.mob.kiipa.model.BaseModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    private static final String TAG = DateUtil.class.getSimpleName();
    public static final String FULL_DATE_FORMAT = "EEE, dd MMM yyyy hh:mm:ss aaa";
    public static final String SLASH_DATE_FORMAT = "dd/MM/yyyy";
    public static final String DB_24_HRS_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_12_HRS_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";

    public static SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(
            DEFAULT_12_HRS_DATE_FORMAT, Locale.US);

    public static String getDateFormat(Date date) {
        DATE_FORMATTER.applyPattern(FULL_DATE_FORMAT);
        return date == null ? null : DATE_FORMATTER.format(date);
    }

    public static String getSlashDateFormat(Date date) {
        DATE_FORMATTER.applyPattern(SLASH_DATE_FORMAT);
        return date == null ? null : DATE_FORMATTER.format(date);
    }

    public static String getDbDateIn24Hrs(Date date) {

        if (date == null) return null;

        try {
            DATE_FORMATTER.applyPattern(DB_24_HRS_DATE_FORMAT);
            return DATE_FORMATTER.format(date);
        } catch (Exception e) {
            Log.i(DateConverter.class.getSimpleName(), "Error parsing date: " + e.getMessage());
            return null;
        }
    }

    public static String getDbDateIn12Hrs(Date date) {

        if (date == null) return null;

        try {
            DATE_FORMATTER.applyPattern(DEFAULT_12_HRS_DATE_FORMAT);
            return DATE_FORMATTER.format(date);
        } catch (Exception e) {
            Log.i(DateConverter.class.getSimpleName(), "Error parsing date: " + e.getMessage());
            return null;
        }
    }

    public static Date convertToDate(String date) {

        if (date == null) return null;

        try {
            String[] dates = date.split(" ");
            String[] datestrs = dates[0].split("-");
            String[] timestr = dates[1].split(":");

            //Calendar month count start from 0
            int month = Integer.valueOf(datestrs[1]);
            if (month > 0) --month;

            Calendar calendar = Calendar.getInstance();
            calendar.set(Integer.valueOf(datestrs[0]),
                    month, Integer.valueOf(datestrs[2]));

            calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(timestr[0]));
            calendar.set(Calendar.MINUTE, Integer.valueOf(timestr[1]));
            calendar.set(Calendar.SECOND, Integer.valueOf(timestr[2]));
            return calendar.getTime();

        } catch (Exception e) {
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
