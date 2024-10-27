package com.pjt.erpit.core.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Custom DateUtils
 */
public class DateUtils {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * "yyyy-MM-dd" 형식의 String 데이터를 LocalDateTime 데이터로 변환
     *
     * @param date p1
     * @return LocalDateTime
     */
    public static LocalDateTime toLocalDate(String date) {
        return LocalDateTime.parse(date + " 00:00:00", formatter);
    }

    /**
     * "yyyy-MM-dd HH:mm:ss" 형식의 String 데이터를 LocalDateTime 데이터로 변환
     *
     * @param date p1
     * @return LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(String date) {
        return LocalDateTime.parse(date, formatter);
    }
}
