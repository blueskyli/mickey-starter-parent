package com.mickey.core.utils.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 日期工具类
 * @author J·K
 * @date 2018/5/7 16:05

 */
public class DateUtils
{

    private static final Logger log = LoggerFactory.getLogger(DateUtils.class);

    private static int FIRST_DATE_OF_WEEK = Calendar.SUNDAY;

    private static Pattern p = Pattern.compile("\\d{4}+[-]\\d{1,2}+[-]\\d{1,2}+");

    public final static String FMT_DEFAULT_DATATIME = "yyyy-MM-dd HH:mm:ss";
    public final static String FMT_FULL_SEQ = "yyyyMMddHHmmssSSS";
    public final static String FMT_YMD_HMS = "yyyyMMddHHmmss";
    public final static String FMT_HHMM = "HH:mm";
    public final static String FMT_YMD = "yyyyMMdd";
    public final static String FMT_YM_NO_SPLIT = "yyyyMM";
    public final static String FMT_YM = "yyyy-MM";
    public final static String FMT_DEFAULT_DATA = "yyyy-MM-dd";
    public final static String FMT_Y_M_D_HM_POINT = "yyyy-MM-dd HH:mm";

    /**
     * 中文周几
     */
    private static String[] WEEK_DAY = new String[]{"周日", "周一", "周二", "周三", "周四", "周五", "周六"};

    /**
     * 默认日期格式
     */
    private static SimpleDateFormat DEFAULT_DATE = new SimpleDateFormat(FMT_DEFAULT_DATA);

    /**
     * 默认日期格式
     */
    private static SimpleDateFormat DEFAULT_DATETIME = new SimpleDateFormat(FMT_DEFAULT_DATATIME);

    /**
     * Formats a Date into a date/time string.
     *
     * @param date
     * @param pattern 格式 yyyyMMddHHmmss / yyMMdd /...
     * @return
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
    /**
     * 获取当前日期
     *
     * @return
     */
    public static Date getCurrentDate() {
        return Calendar.getInstance().getTime();
    }

    /**
     * 转换日期格式(yyyy-MM-dd HH:mm:ss)
     *
     * @param date
     * @return
     */
    public static String getFullFormatDate(Date date) {
        if (date == null) {
            return "";
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(FMT_DEFAULT_DATATIME);
            String strDate = sdf.format(date);
            return strDate;
        } catch (Exception e) {
            log.error("日期格转换失败:" + e);
        }
        return "";
    }

    /**
     * 获取当前年份
     *
     * @return
     */
    public static int getCurrentYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    /**
     * 获取当前日期毫秒数
     *
     * @return
     */
    public static Long getCurrentDateMill() {
        return Calendar.getInstance().getTimeInMillis();
    }

    /**
     * 当前日期字符串
     *
     * @return
     */
    public static String getCurrentDateStr(String pattern) {
        SimpleDateFormat sdf;
        if (pattern == null) {
            sdf = new SimpleDateFormat(FMT_DEFAULT_DATA);
        } else {
            sdf = new SimpleDateFormat(pattern);
        }
        return sdf.format(new Date());
    }

    /**
     * 时间添加分钟
     *
     * @param min
     * @return
     */
    public static Date addMinutes(int min) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, min);
        return cal.getTime();
    }

    /**
     * 时间添加分钟
     *
     * @param min
     * @return
     */
    public static Date addMinutes(Date date, int min) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, min);
        return cal.getTime();
    }

    /**
     * 时间添加月
     *
     * @param month
     * @return
     */
    public static Date addMonth(Date date, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, month);
        return cal.getTime();
    }

    /**
     * 时间添加日
     *
     * @param day
     * @return
     */
    public static Date addDay(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, day);
        return cal.getTime();
    }

    /**
     * 时间添加小时
     *
     * @param hour
     * @return
     */
    public static Date addHour(Date date, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, hour);
        return cal.getTime();
    }

    /**
     * 默认格式转换成日期串
     *
     * @param str
     * @return
     */
    public static Date parseToDate(String str) {
        try {
            Date date = new SimpleDateFormat(FMT_DEFAULT_DATA).parse(str);
            return date;
        } catch (Exception e) {
            log.error("日期格转换失败: " + e);
        }
        return null;
    }

    /**
     * 默认格式转换成日期串
     *
     * @param str
     * @param pattern
     * @return
     */
    public static Date parseToDate(String str, String pattern) {
        SimpleDateFormat sdf;
        if (pattern == null || pattern.length() == 0) {
            sdf = new SimpleDateFormat(FMT_DEFAULT_DATATIME);
            ;
        } else {
            sdf = new SimpleDateFormat(pattern);
        }

        try {
            Date date = sdf.parse(str);
            return date;
        } catch (Exception e) {
            log.error("日期格转换失败: {}", e.getMessage());
        }

        return null;
    }

    public static String getFormatDate(Date date) {
        if (date == null) {
            return "";
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(FMT_DEFAULT_DATATIME);
            String strDate = sdf.format(date);
            return strDate;
        } catch (Exception e) {
            log.error("日期格转换失败: {}", e.getMessage());
        }
        return "";
    }

    /**
     * 转换日期格式
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String getFormatDate(Date date, String pattern) {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("pattern");
        }

        if (date == null) {
            return "";
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            String strDate = sdf.format(date);
            return strDate;
        } catch (Exception e) {
            log.error("日期格转换失败: {}", e);
        }
        return "";
    }

    /**
     * 判断当前日期是否为月初
     *
     * @return 结果
     */
    public static boolean isMonthBegin() {
        Calendar cal = Calendar.getInstance();
        boolean isMonthBegin = false;
        // 判断是否为每月的1号
        if (cal.get(Calendar.DAY_OF_MONTH) == 1) {
            isMonthBegin = true;
        }
        return isMonthBegin;
    }

    /**
     * 判断当前日期是否为周一
     *
     * @return 结果
     */
    public static boolean isWeekBegin() {
        Calendar cal = Calendar.getInstance();
        boolean isWeekBegin = false;
        // 判断是否为周一
        if (cal.get(Calendar.DAY_OF_WEEK) == 2) {
            isWeekBegin = true;
        }
        return isWeekBegin;
    }

    /**
     * 判断日期是否合法（yyyy-MM-dd）
     *
     * @param dateString 日期字符串
     * @return
     */
    public static boolean validateDate(String dateString) {
        // 使用正则表达式 测试 字符 符合dddd-dd-dd 的格式(d表示数字)
        Matcher m = p.matcher(dateString);
        if (!m.matches()) {
            return false;
        }

        // 得到年月日
        String[] array = dateString.split("-");
        int year = Integer.valueOf(array[0]);
        int month = Integer.valueOf(array[1]);
        int day = Integer.valueOf(array[2]);

        if (month < 1 || month > 12) {
            return false;
        }
        int[] monthLengths = new int[]{0, 31, -1, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (isLeapYear(year)) {
            monthLengths[2] = 29;
        } else {
            monthLengths[2] = 28;
        }
        int monthLength = monthLengths[month];
        if (day < 1 || day > monthLength) {
            return false;
        }
        return true;
    }

    /**
     * 是否为闰年
     *
     * @param year 年份
     * @return 是否闰年
     */
    private static boolean isLeapYear(int year) {
        return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
    }

    /**
     * 俩时间比较 0:dt1=dt2 大于0：dt1>dt2 小于0：dt1<dt2
     *
     * @param dt1 时间1
     * @param dt2 时间2
     * @return
     */
    public static int compareDate(Date dt1, Date dt2) {
        int flag = 0;
        String date1 = new SimpleDateFormat(FMT_DEFAULT_DATA).format(dt1);
        String date2 = new SimpleDateFormat(FMT_DEFAULT_DATA).format(dt2);
        flag = date1.compareTo(date2);
        return flag;
    }

    /**
     * 俩时间比较 0:dt1=dt2 大于0：dt1>dt2 小于0：dt1<dt2
     *
     * @param dt1 时间1
     * @param dt2 时间2
     * @return
     */
    public static boolean compareDateTime(Date dt1, Date dt2) {
        return dt1.getTime() > dt2.getTime();
    }

    /**
     * 获取中文周几
     *
     * @param date
     * @return
     */
    public static String getChinaeseWeekDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1 < 0 ? 0 : cal.get(Calendar.DAY_OF_WEEK) - 1;
        return WEEK_DAY[w];
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(FMT_DEFAULT_DATA);
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 判断是否为一周的最后一天(目前配置的是周日为一周的第一天)
     *
     * @param date
     * @return
     */
    public static boolean isEndOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        int weekDay = cal.get(Calendar.DAY_OF_WEEK);
        if (weekDay == FIRST_DATE_OF_WEEK) {
            return true;
        }
        return false;
    }

    /**
     * 判断时间是否为月末
     *
     * @param nowDate 日期（需要验证的日期）
     * @return boolean true 表示是月末 false 表示不为月末
     */
    public static boolean isMonthEnd(Date nowDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        if (day == 1) {
            return true;
        }
        return false;
    }

    /**
     * 判断时间是否为季末
     *
     * @param nowDate 日期（需要验证的日期）
     * @return boolean true 表示是季末 false 表示不是季末
     */
    public static boolean isQuarterEnd(Date nowDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        int month = cal.get(Calendar.MONTH);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        if (day == 1 && (month == Calendar.MARCH || month == Calendar.JUNE || month == Calendar.SEPTEMBER || month == Calendar.DECEMBER)) {
            return true;
        }
        return false;
    }

    /**
     * 判断时间是否为季出
     *
     * @param nowDate 日期（需要验证的日期）
     * @return boolean true 表示是季初 false 表示不是季初
     */
    public static boolean isQuarterBegin(Date nowDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        if (day == 1 && (month == Calendar.JANUARY || month == Calendar.APRIL || month == Calendar.JULY || month == Calendar.OCTOBER)) {
            return true;
        }
        return false;
    }

    /**
     * 判断时间是否为半年末
     *
     * @param nowDate 日期（需要验证的日期）
     * @return boolean true 表示是半年末 false 表示不是半年末
     */
    public static boolean isHalfYearEnd(Date nowDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        int month = cal.get(Calendar.MONTH);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        if (day == 1 && (month == Calendar.JUNE || month == Calendar.DECEMBER)) {
            return true;
        }
        return false;
    }

    /**
     * 判断时间是否为半年出
     *
     * @param nowDate 日期（需要验证的日期）
     * @return boolean true 表示是半年初 false 表示不是半年初
     */
    public static boolean isHalfYearBegin(Date nowDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        if (day == 1 && (month == Calendar.JANUARY || month == Calendar.JULY)) {
            return true;
        }
        return false;
    }

    /**
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return
     * @Description: 获取相差分钟数
     * @author clg
     * @date 2016年6月1日 下午6:11:45
     */
    public static int getDifferMinute(Date beginTime, Date endTime) {
        long seconds = endTime.getTime() - beginTime.getTime();
        return ((int) seconds / 60) / 1000;
    }

    /**
     * 两个日期的中间日期计算
     *
     * @param biginDate
     * @param endDate
     * @return
     */
    public static Date getTwoDateCenter(Date biginDate, Date endDate) {
        long millis = biginDate.getTime() + (endDate.getTime() - biginDate.getTime());
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millis);
        return c.getTime();
    }

    public static LocalDate Date2LocalDate(Date endTime) {
        Instant instant = endTime.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
        return localDate;
    }

    public static Date localDateToDate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    public static String getStartOfDay(String date, String pattern) {
        return getStartOfDay(parseToDate(date,pattern));
    }

    public static String getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        calendar.set(year, month, day, 0, 0, 0);
        return format(calendar.getTime(),FMT_DEFAULT_DATATIME);
    }

    public static String getEndOfDay(String date, String pattern) {
        return getEndOfDay(parseToDate(date,pattern));
    }

    public static String getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        calendar.set(year, month, day, 23, 59, 59);
        return format(calendar.getTime(),FMT_DEFAULT_DATATIME);
    }

    /**
     * 计算两个时间差（年，月，星期，日，时，分，秒）
     * J·K
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static String calculateTimeDifference(Date startDate, Date endDate, DateFunction<Long, Long, Long, Long, Long, Long, String> function) {
        if (null == startDate || null == endDate) {
            return "";
        }
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime fromDateTime = LocalDateTime.ofInstant(startDate.toInstant(), zoneId);
        LocalDateTime toDateTime = LocalDateTime.ofInstant(endDate.toInstant(), zoneId);

        LocalDateTime tempDateTime = LocalDateTime.from(fromDateTime);

        long years = tempDateTime.until(toDateTime, ChronoUnit.YEARS);
        tempDateTime = tempDateTime.plusYears(years);

        long months = tempDateTime.until(toDateTime, ChronoUnit.MONTHS);
        tempDateTime = tempDateTime.plusMonths(months);

        long days = tempDateTime.until(toDateTime, ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusDays(days);

        long hours = tempDateTime.until(toDateTime, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours(hours);

        long minutes = tempDateTime.until(toDateTime, ChronoUnit.MINUTES);
        tempDateTime = tempDateTime.plusMinutes(minutes);

        long seconds = tempDateTime.until(toDateTime, ChronoUnit.SECONDS);

        return function.apply(years, months, days, hours, minutes, seconds);
    }

    public static void main(String[] args) throws ParseException {
        log.info("{}",DateUtils.format(new Date(),DateUtils.FMT_DEFAULT_DATATIME));
        log.info("{}",DateUtils.getCurrentDate());
        log.info("{}",DateUtils.getCurrentDate());
        log.info("{}",DateUtils.getCurrentYear());
        log.info("{}",DateUtils.getCurrentDateStr("yyyy-MM-dd"));
        log.info("{}",DateUtils.addMinutes(10));
        log.info("{}",DateUtils.addMinutes(new Date(),10));
        log.info("{}",DateUtils.addMonth(new Date(),10));
        log.info("{}",DateUtils.addDay(new Date(),10));
        log.info("{}",DateUtils.addHour(new Date(),10));
        log.info("{}",DateUtils.parseToDate("2020-02-01"));
        log.info("{}",DateUtils.parseToDate("2020-02-01","yyyy-MM-dd"));
        log.info("{}",DateUtils.getFormatDate(new Date()));
        log.info("{}",DateUtils.getFormatDate(new Date(),"yyyy-MM-dd"));
        log.info("{}",DateUtils.getFormatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
        log.info("{}",DateUtils.isMonthBegin());
        log.info("{}",DateUtils.isWeekBegin());
        log.info("{}",DateUtils.compareDate(new Date(),new Date()));
        log.info("{}",DateUtils.getChinaeseWeekDay(new Date()));
        log.info("{}",DateUtils.daysBetween(new Date(),new Date()));
        log.info("{}",DateUtils.isEndOfWeek(new Date()));
        log.info("{}",DateUtils.isMonthEnd(new Date()));
        log.info("{}",DateUtils.isQuarterEnd(new Date()));
        log.info("{}",DateUtils.isQuarterBegin(new Date()));
        log.info("{}",DateUtils.isHalfYearEnd(new Date()));
        log.info("{}",DateUtils.isHalfYearBegin(new Date()));
        log.info("{}",DateUtils.getDifferMinute(new Date(),new Date()));
        log.info("{}",DateUtils.Date2LocalDate(new Date()));
        log.info("{}",DateUtils.localDateToDate(DateUtils.Date2LocalDate(new Date())));

        log.info("{}",DateUtils.getStartOfDay(new Date()));
        log.info("{}",DateUtils.getStartOfDay("2020-01-01 11:11:11",FMT_DEFAULT_DATATIME));
        log.info("{}",DateUtils.getStartOfDay("2020-01-01",FMT_DEFAULT_DATA));
        log.info("{}",DateUtils.getStartOfDay("2020-01-01 11:11:11",FMT_DEFAULT_DATA));
        log.info("{}",DateUtils.getEndOfDay(new Date()));
        log.info("{}",DateUtils.getEndOfDay("2020-01-01 11:11:11",FMT_DEFAULT_DATATIME));
        log.info("{}",DateUtils.getEndOfDay("2020-01-01",FMT_DEFAULT_DATA));
        log.info("{}",DateUtils.getEndOfDay("2020-01-01 11:11:11",FMT_DEFAULT_DATA));

        String s = DateUtils.calculateTimeDifference(DateUtils.parseToDate("2020-12-27 12:12:12", DateUtils.FMT_DEFAULT_DATATIME), new Date(),
            (years, months, days, hours, minutes, seconds) -> (0 == years ? "" : years + "年")
                + (0 == months ? "" : months + "月")
                + (0 == days ? "" : days + "天")
                + (hours + "小时")
                + (minutes + "分钟"));

        log.info(s);
    }
}
