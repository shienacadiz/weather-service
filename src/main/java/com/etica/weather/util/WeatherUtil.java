package com.etica.weather.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class WeatherUtil {

    public static ZoneOffset convertUtcToZoneOffset(long timeInUtc) {
        long offset = timeInUtc / 60 / 60;
        return ZoneOffset.ofHours(Math.toIntExact(offset));
    }

    public static LocalDateTime convertUtcToLocalDateTime(long timeInUtc, ZoneOffset offset) {
        Date sunset = new Date(timeInUtc * 1000);
        return sunset.toInstant().atZone(offset.normalized()).toLocalDateTime();
    }
}
