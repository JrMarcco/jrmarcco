package com.jrmarcco.common.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author hongjc
 * @version 1.0  2019/2/11
 */
public class DateUtils {

    private static final ZoneId DEFAULT_ZONE_ID = ZoneId.systemDefault();

    public static Date toDate(LocalDateTime dateTime, ZoneId zoneId) {
        return Date.from(dateTime.atZone(zoneId).toInstant());
    }

    public static Date toDate(LocalDateTime dateTime) {
        return toDate(dateTime, DEFAULT_ZONE_ID);
    }
}
