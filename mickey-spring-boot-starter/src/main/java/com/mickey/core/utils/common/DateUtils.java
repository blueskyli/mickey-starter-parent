package com.mickey.core.utils.common;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

    /**
     * 全日期格式
     */
    public static String fullPattern = "yyyy-MM-dd HH:mm:ss";

    /**
     * 全日期格式
     */
    public static String fullPattern1 = "yyyy/MM/dd HH:mm:ss";
    /**
     * 时分格式
     */
    public static String hhmmPattern = "HH:mm";
    /**
     * 全日期格式（没有间隔符号）
     */
    public static String FMT_YMD_HMS = "yyyyMMddHHmmss";
    /**
     * yyyy-MM格式
     */
    public static String FMT_YM = "yyyy-MM";
    /**
     * yyyyMMdd格式
     */
    public static String FMT_YMD = "yyyyMMdd";
    /**
     * yyyyMM格式
     */
    public static String FMT_YM_NO_SPLIT = "yyyyMM";

    /**
     * 默认日期格式
     */
    public static String defaultPattern = "yyyy-MM-dd";
    /**
     * 默认日期格式
     */
    public static String defaultPattern1 = "yyyy/MM/dd";

    /**
     * yyyy.MM.dd格式
     */
    public static String FMT_YMD_POINT = "yyyy.MM.dd";

    /**
     * yyyy.MM.dd HH:mm格式
     */
    public static String FMT_YMD_HM_POINT = "yyyy.MM.dd HH:mm";

    /**
     * yyyy-MM-dd HH:mm格式
     */
    public static String FMT_Y_M_D_HM_POINT = "yyyy-MM-dd HH:mm";

    /**
     * MM.dd格式
     */
    public static String FMT_MD_POINT = "MM.dd";

    /**
     * yyMMdd格式
     */
    public static String FMT_YYMMDD = "yyMMdd";

    /**
     * yyyy.MM.dd格式
     */
    public static String FMT_YYMMDD_POINT = "yyyy.MM.dd";

    /**
     * 中文周几
     */
    private static String[] WEEK_DAY = new String[]{"周日", "周一", "周二", "周三", "周四", "周五", "周六"};

    /**
     * 默认日期格式
     */
    private static SimpleDateFormat DEFAULT_DATE = new SimpleDateFormat(defaultPattern);

    /**
     * 默认日期格式
     */
    private static SimpleDateFormat DEFAULT_TIME = new SimpleDateFormat(hhmmPattern);

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
            sdf = new SimpleDateFormat(defaultPattern);
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
            Date date = new SimpleDateFormat(defaultPattern).parse(str);
            return date;
        } catch (Exception e) {
            log.error("日期格转换失败: " + e);
        }
        return null;
    }

    /**
     * 默认格式转换成日期串
     *
     * @param dt
     * @return
     */
    public static Date parseToDate(Date dt) {
        try {
            String str = new SimpleDateFormat(defaultPattern).format(dt);
            Date date = new SimpleDateFormat(defaultPattern).parse(str);
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
            sdf = new SimpleDateFormat(defaultPattern);
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

    /**
     * 字符串-日期-格式化日期串
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static String getFormatDate(String dateStr, String pattern) {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("pattern");
        }
        try {
            String date = new SimpleDateFormat(defaultPattern).format(new SimpleDateFormat(defaultPattern).parse(dateStr));
            return date;
        } catch (Exception e) {
            log.error("日期格转换失败: {}", e.getMessage());
        }
        return "";
    }

    public static String getFormatDate(Date date) {
        if (date == null) {
            return "";
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(defaultPattern);
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
            SimpleDateFormat sdf = new SimpleDateFormat(fullPattern);
            String strDate = sdf.format(date);
            return strDate;
        } catch (Exception e) {
            log.error("日期格转换失败:" + e);
        }
        return "";
    }

    /**
     * 转换日期格式
     *
     * @param sdf
     * @param date
     * @return
     */
    public static String format(SimpleDateFormat sdf, Date date) {
        if (date == null) {
            return "";
        }

        return sdf.format(date);
    }

    /**
     * 尝试转换字符串到日期
     *
     * @param strDate 日期字符串
     * @param sdf     日期格式
     * @return
     */
    public static Date tryParse(SimpleDateFormat sdf, String strDate) {
        if (sdf == null || strDate == null) {
            return null;
        }
        Date ret = null;
        try {
            ret = sdf.parse(strDate);
        } catch (ParseException e) {
            log.error("日期格转换失败:" + e);
            // 忽略解析错误
        }
        return ret;
    }

    /**
     * 将整型的时间类型转换成字符串
     *
     * @param to
     * @return
     */
    public static String formatTime(Integer to) {
        String str = String.format("%04d", to);
        return str.replaceFirst("^.*(\\d{2})(\\d{2})$", "$1:$2");
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
        String date1 = new SimpleDateFormat(defaultPattern).format(dt1);
        String date2 = new SimpleDateFormat(defaultPattern).format(dt2);
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
     * 俩时间比较 0:dt1=dt2 大于0：dt1>dt2 小于0：dt1<dt2
     *
     * @param dt1 时间1
     * @param dt2 时间2
     * @return
     */
    public static int compareTime(Date dt1, Date dt2) {
        int flag = 0;
        String date1 = new SimpleDateFormat(hhmmPattern).format(dt1);
        String date2 = new SimpleDateFormat(hhmmPattern).format(dt2);
        flag = date1.compareTo(date2);
        return flag;
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
        SimpleDateFormat sdf = new SimpleDateFormat(defaultPattern);
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
     * 判断时间是否为年末
     *
     * @param nowDate 日期（需要验证的日期）
     * @return boolean true 表示是年末 false 表示不为年末
     */
    public static boolean isYearEnd(Date nowDate) {
        if ("1231".equals(format(nowDate, "MMdd"))) {
            return true;
        }
        return false;
    }

    /**
     * 判断时间是否为年初
     *
     * @param nowDate 日期（需要验证的日期）
     * @return boolean true 表示是年初 false 表示不为年初
     */
    public static boolean isYearBegin(Date nowDate) {
        if ("0101".equals(format(nowDate, "MMdd"))) {
            return true;
        }
        return false;
    }

    // 去年的今天
    public static String getNowOfLastYear() {
        SimpleDateFormat aSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        GregorianCalendar aGregorianCalendar = new GregorianCalendar();
        aGregorianCalendar.set(Calendar.YEAR, aGregorianCalendar.get(Calendar.YEAR) - 1);
        String currentYearAndMonth = aSimpleDateFormat.format(aGregorianCalendar.getTime());
        return currentYearAndMonth;
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
     * 功能:暂定月差计算方法 作者: wangnings 创建日期:2016年6月4日 修改者: mender 修改日期: modifydate
     *
     * @param biginDate 开始时间
     * @param endDate   结束时间
     * @return
     * @throws ParseException
     */
    public static int monthBetween(Date biginDate, Date endDate) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(biginDate);
        int biginYear = c.get(Calendar.YEAR);
        int biginMonth = c.get(Calendar.MONTH);
        c.setTime(endDate);
        int endYear = c.get(Calendar.YEAR);
        int endMonth = c.get(Calendar.MONTH);

        return (endYear - biginYear) * 12 + (endMonth - biginMonth);
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

    /**
     * @param str
     * @return
     * @Description: 转换时间格式
     * @author clg
     * @date 2016年7月16日 下午3:48:35
     */
    public static String formatDate(String str, String fmtOld, String fmtNew) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        SimpleDateFormat oldDate = new SimpleDateFormat(fmtOld);
        SimpleDateFormat newDate = new SimpleDateFormat(fmtNew);
        String returnDate = "";
        try {
            returnDate = newDate.format(oldDate.parse(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnDate;
    }
}
