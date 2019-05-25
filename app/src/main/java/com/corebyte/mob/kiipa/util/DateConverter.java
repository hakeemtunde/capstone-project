package com.corebyte.mob.kiipa.util;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class DateConverter {

    @TypeConverter
    public static Date toDate(String date) {
        return date ==null ? null : DateUtil.convertToDate(date);
    }

    @TypeConverter
    public static String toTimestamp(Date date) {
        return date == null ? null : DateUtil.getDbDateIn24Hrs(date);
    }
}
