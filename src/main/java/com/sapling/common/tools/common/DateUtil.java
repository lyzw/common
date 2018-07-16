package com.sapling.common.tools.common;

import java.util.Calendar;
import java.util.Date;

/**
 * @author zhouwei
 * @version v1.0
 * @date 2018/6/7
 * @since v1.0
 */
public class DateUtil {

    public static Date yesterday() {
        return addDay(new Date(), -1);
    }

    public static Date tomorrow() {
        return addDay(new Date(), 1);
    }

    public static Date nextDay(Date date) {
        return addDay(date, 1);
    }

    public static Date dayBefore(Date date) {
        return addDay(date, -1);
    }

    public static Date addDay(Date date, int day) {
        return addDate(date, 0, 0, day, 0, 0, 0, 0);
    }

    public static Date setAndAddDate(Date date, boolean addFirst,
                                     Integer setYear, Integer setmonth, Integer setday,
                                     Integer sethour, Integer setminuter, Integer setsecond, Integer setmilliSecond,
                                     Integer addyear, Integer addmonth, Integer addday,
                                     Integer addhour, Integer addminuter, Integer addsecond, Integer addmilliSecond) {
        if (addFirst) {
            return setDate(
                    addDate(date, addyear, addmonth, addday, addhour, addminuter, addsecond, addmilliSecond),
                    setYear, setmonth, setday, sethour, setminuter, setsecond, setmilliSecond);
        } else {
            return addDate(
                    setDate(date, setYear, setmonth, setday, sethour, setminuter, setsecond, setmilliSecond),
                    addyear, addmonth, addday, addhour, addminuter, addsecond, addmilliSecond);
        }
    }

    public static Date addDate(Date date, Integer year, Integer month, Integer day, Integer hour, Integer minuter, Integer second, Integer milliSecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.DATE, day);
        calendar.add(Calendar.HOUR, hour);
        calendar.add(Calendar.MINUTE, minuter);
        calendar.add(Calendar.SECOND, second);
        calendar.add(Calendar.MILLISECOND, milliSecond);
        return calendar.getTime();
    }

    public static Date dateEnding(Date date) {
        return setDate(date, null, null, null, 59, 59, 59, 999);
    }

    public static Date dateEnding() {
        return dateEnding(new Date());
    }

    public static Date nextMonth(Date date) {
        return addMonth(date, 1);
    }

    public static Date monthEnding(Date date) {
        return setAndAddDate(addMonth(date, 1), false, null, 0, 1,
                23, 59, 59, 999,
                0, 0, -1,
                0, 0, 0, 0);
    }

    public static Date addMonth(Date date, Integer addMonth) {
        return addDate(date, 0, addMonth, 0, 0, 0, 0, 0);
    }

    public static Date dateBegining() {
        return dateBegining(new Date());
    }

    public static Date dateBegining(Date date) {
        return setDate(date, null, null, null, 0, 0, 0, 0);
    }

    public static Date monthBeginning(Date date) {
        return setDate(date, null, null, 1, 0, 0, 0, 0);
    }

    public static Date yearBegining(Date date) {
        return setDate(date, null, Calendar.JANUARY, 1, 0, 0, 0, 0);
    }

    public static Date setDate(Date date, Integer year, Integer month, Integer day, Integer hour, Integer minuter, Integer second, Integer millisecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (year != null) {
            calendar.set(Calendar.YEAR, year);
        }
        if (month != null) {
            calendar.set(Calendar.MONTH, month);
        }
        if (day != null) {
            calendar.set(Calendar.DATE, day);
        }
        if (hour != null) {
            calendar.set(Calendar.HOUR, hour);
        }
        if (minuter != null) {
            calendar.set(Calendar.MINUTE, minuter);
        }
        if (second != null) {
            calendar.set(Calendar.SECOND, second);
        }
        if (millisecond != null) {
            calendar.set(Calendar.MILLISECOND, millisecond);
        }
        return calendar.getTime();
    }

    public static Long toUnixTimestamp(Date date) {
        return date.getTime() / 1000;
    }

    public static Date fromUnixTimestamp(Long timestamp) {
        return fromTimestamp(timestamp * 1000);
    }

    public static Date fromTimestamp(Long timestamp) {
        Date date = new Date();
        date.setTime(timestamp);
        return date;
    }

    /**
     * 将influx时序数据库的时间戳转换为日期
     * @param timestamp
     * @return
     */
    public static Date fromInfluxdbTimestamp(Long timestamp){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp/1000000);
        return calendar.getTime();
    }

}
