package com.agent.common.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author lll
 */
@Slf4j
@UtilityClass
public class DateUtil extends DateUtils {

    /**
     * 获取当天开始 UTC0
     *
     * @param date
     * @return
     */
    public static Date getDayBegin(Date date) {
        String utcDate = DateFormatUtils.formatUTC(date, "yyyy-MM-dd HH:mm");
        DateTime utcDateTime = cn.hutool.core.date.DateUtil.parseDate(utcDate);
        return cn.hutool.core.date.DateUtil.beginOfDay(utcDateTime).offset(DateField.HOUR_OF_DAY, 8);
    }

    /**
     * 获取本周开始时间 UTC0
     *
     * @param date
     * @return
     */
    public static Date getWeekBegin(Date date) {
        String utcDate = DateFormatUtils.formatUTC(date, "yyyy-MM-dd HH:mm");
        DateTime utcDateTime = cn.hutool.core.date.DateUtil.parseDate(utcDate);
        return cn.hutool.core.date.DateUtil.beginOfWeek(utcDateTime).offset(DateField.HOUR_OF_DAY, 8);
    }

    /**
     * 获取本月开始时间 UTC0
     *
     * @param date
     * @return
     */
    public static Date getMonthBegin(Date date) {
        String utcDate = DateFormatUtils.formatUTC(date, "yyyy-MM-dd HH:mm");
        DateTime utcDateTime = cn.hutool.core.date.DateUtil.parseDate(utcDate);
        return cn.hutool.core.date.DateUtil.beginOfMonth(utcDateTime).offset(DateField.HOUR_OF_DAY, 8);
    }

    /**
     * LocalDateTime转为日期
     *
     * @param localDateTime LocalDateTime
     * @return 日期
     */
    public static Date localDateTimeToDate(final LocalDateTime localDateTime) {
        if (null == localDateTime) {
            return null;
        }
        final ZonedDateTime zdt = localDateTime.atZone(ZoneOffset.ofHours(8));
        final Date date = Date.from(zdt.toInstant());
        return date;
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();

        return instant.atZone(zoneId).toLocalDateTime();
    }

    public static Date getNowDate() {
        return new Date();
    }

    public static String dateToString(Date date) {
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf3.format(date);
    }

    public static Date stringToDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(date);
        } catch (ParseException e) {
            log.info("日期转换错误,{},{}", date, e.getMessage());
        }
        return null;
    }

    public static boolean isTimestampInMilliseconds(long timestamp) {
        String timestampString = Long.toString(timestamp);
        return timestampString.length() == 13;
    }

    /**
     * date2Timestamp
     *
     * @param date
     * @return
     */
    public Long date2Timestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    /**
     * timestamp2Date
     *
     * @param timestamp
     * @return
     */
    public Date timestamp2Date(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    /**
     * date2Second
     *
     * @param date
     * @return
     */
    public Long date2Second(Date date) {
        if (date == null) {
            return null;
        }
        return date2Timestamp(date) / 1000L;
    }

    /**
     * second2Date
     *
     * @param seconds
     * @return
     */
    public Date second2Date(Long seconds) {
        return seconds == null ? null : timestamp2Date(seconds * 1000L);
    }
}
