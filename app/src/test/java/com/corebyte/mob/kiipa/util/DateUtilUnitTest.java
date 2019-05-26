package com.corebyte.mob.kiipa.util;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DateUtilUnitTest {

    @Test
    public void getStringDateTest() {

        String dateStr24hrs = "2019-05-25 15:48:33";
        String dateStr12hrs = "2019-05-25 03:48:33";

        Date actualDate = DateUtil.convertToDate(dateStr24hrs);

        String actual24hrsDateStr = DateUtil.getDbDateIn24Hrs(actualDate);
        String actual12hrsDateStr = DateUtil.getDbDateIn12Hrs(actualDate);

        assertNotNull(actualDate);
        assertEquals(dateStr24hrs, actual24hrsDateStr);
        assertEquals(dateStr12hrs, actual12hrsDateStr);
    }
}
