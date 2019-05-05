package com.corebyte.mob.kiipa.util;

import com.corebyte.mob.kiipa.model.BaseModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    private static final String DATE_FORMAT_1 = "EEE, dd MMM yyyy HH:mm:ss aaa";
    private static final String DATE_FORMAT_2 = "dd/MM/yyyy";

    private static SimpleDateFormat dateFormat =
            new SimpleDateFormat(DATE_FORMAT_1, Locale.getDefault());

    public static String getDateFormat(Date date) {
        return date == null ? null : dateFormat.format(date);
    }

    public static String getDateFormat2(Date date) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_2, Locale.getDefault());
        return date == null ? null : simpleDateFormat.format(date);
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
