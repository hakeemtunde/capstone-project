package com.corebyte.mob.kiipa.util;

import android.arch.persistence.room.TypeConverter;
import android.util.Log;

import java.util.Date;

public class DateConverter {

    @TypeConverter
    public static Date toDate(String date) {
        return date ==null ? null : DateUtil.getStringDate(date);
    }

    @TypeConverter
    public static String toTimestamp(Date date) {
        return date == null ? null : DateUtil.getDateString(date);
    }
}
