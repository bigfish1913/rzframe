package com.rz.frame.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateUtils {


    public static long locateTimeToUnixTime(LocalDateTime localDateTime) {

        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public static LocalDateTime unixTimdToLocateTime(long unixTime) {
        Long timestamp = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        LocalDateTime localDateTime =LocalDateTime.ofEpochSecond(timestamp/1000,0,ZoneOffset.ofHours(8));
        return localDateTime;
    }
}
