package com.shia.library.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

    public static final String FORMAT_DATE = "yyyy-MM-dd";

    public static final String FORMAT_TIME = "HH:mm:ss";

    public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    public static final SimpleDateFormat SDFD = new SimpleDateFormat(FORMAT_DATE);

    public static final SimpleDateFormat SDFT = new SimpleDateFormat(FORMAT_TIME);

    public static final SimpleDateFormat SDFDT = new SimpleDateFormat(FORMAT_DATE_TIME);

    /**
     * 得到以yyyy-MM-dd格式表示的当前日期字符串
     */
    public static String getCurrentDate() {
        // return sdfd.format(new Date());
        GregorianCalendar tGCalendar = new GregorianCalendar();
        StringBuffer tStringBuffer = new StringBuffer(10);
        int sYears = tGCalendar.get(Calendar.YEAR);
        tStringBuffer.append(sYears);
        tStringBuffer.append('-');
        int sMonths = tGCalendar.get(Calendar.MONTH) + 1;
        if (sMonths < 10) {
            tStringBuffer.append('0');
        }
        tStringBuffer.append(sMonths);
        tStringBuffer.append('-');
        int sDays = tGCalendar.get(Calendar.DAY_OF_MONTH);
        if (sDays < 10) {
            tStringBuffer.append('0');
        }
        tStringBuffer.append(sDays);
        String tString = tStringBuffer.toString();
        return tString;
    }

    /**
     * 得到以format格式表示的当前日期字符串
     */
    public static String getCurrentDate(String format) {
        SimpleDateFormat t = new SimpleDateFormat(format);
        return t.format(new Date());
    }

    /**
     * 得到以HH:mm:ss表示的当前时间字符串
     */
    public static String getCurrentTime() {
        // return sdft.format(new Date());
        GregorianCalendar tGCalendar = new GregorianCalendar();
        StringBuffer tStringBuffer = new StringBuffer(8);
        int sHOUR = tGCalendar.get(Calendar.HOUR_OF_DAY);
        if (sHOUR < 10) {
            tStringBuffer.append('0');
        }
        tStringBuffer.append(sHOUR);
        tStringBuffer.append(':');
        int sMINUTE = tGCalendar.get(Calendar.MINUTE);
        if (sMINUTE < 10) {
            tStringBuffer.append('0');
        }
        tStringBuffer.append(sMINUTE);
        tStringBuffer.append(':');
        int sSECOND = tGCalendar.get(Calendar.SECOND);
        if (sSECOND < 10) {
            tStringBuffer.append('0');
        }
        tStringBuffer.append(sSECOND);
        String tString = tStringBuffer.toString();
        return tString;
    }

    /**
     * 得到以format格式表示的当前时间字符串
     */
    public static String getCurrentTime(String format) {
        SimpleDateFormat t = new SimpleDateFormat(format);
        return t.format(new Date());
    }

    /**
     * 得到以yyyy-MM-dd HH:mm:ss表示的当前时间字符串
     */
    public static String getCurrentDateTime() {
        String format = DateUtil.FORMAT_DATE + " " + DateUtil.FORMAT_TIME;
        return getCurrentDateTime(format);
    }

    public static int getDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static int getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static int getDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static int getDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    // 获取某一个月的天数
    public static int getMaxDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getActualMaximum(Calendar.DATE);
    }

    // 获取某月的第一天
    public static String getFirstDayOfMonth(String date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(parse(date));
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return SDFD.format(cal.getTime());
    }

    public static int getDayOfYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DAY_OF_YEAR);
    }

    public static int getDayOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_YEAR);
    }

    public static int getDayOfWeek(String date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(parse(date));
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static int getDayOfMonth(String date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(parse(date));
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static int getDayOfYear(String date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(parse(date));
        return cal.get(Calendar.DAY_OF_YEAR);
    }

    public static String getCurrentDateTime(String format) {
        SimpleDateFormat t = new SimpleDateFormat(format);
        return t.format(new Date());
    }

    /**
     * 输出只带日期的字符串
     */
    public static String toString(Date date) {
        if (date == null) {
            return "";
        }
        return SDFD.format(date);
    }

    /**
     * 输出带有日期和时间的字符串
     */
    public static String toDateTimeString(Date date) {
        if (date == null) {
            return "";
        }
        return SDFDT.format(date);
    }

    /**
     * 按指定的format输出日期字符串
     */
    public static String toString(Date date, String format) {
        SimpleDateFormat t = new SimpleDateFormat(format);
        return t.format(date);
    }

    /**
     * 输出只带时间的字符串
     */
    public static String toTimeString(Date date) {
        if (date == null) {
            return "";
        }
        return SDFDT.format(date);
    }

    public static int compare(String date1, String date2) {
        return compare(date1, date2, FORMAT_DATE);
    }

    public static int compareTime(String time1, String time2) {
        return compareTime(time1, time2, FORMAT_TIME);
    }

    public static int compare(String date1, String date2, String format) {
        Date d1 = parse(date1, format);
        Date d2 = parse(date2, format);
        return d1.compareTo(d2);
    }

    public static int compareTime(String time1, String time2, String format) {
        String[] arr1 = time1.split(":");
        String[] arr2 = time2.split(":");
        if (arr1.length < 2) {
            throw new RuntimeException("错误的时间值:" + time1);
        }
        if (arr2.length < 2) {
            throw new RuntimeException("错误的时间值:" + time2);
        }
        int h1 = Integer.parseInt(arr1[0]);
        int m1 = Integer.parseInt(arr1[1]);
        int h2 = Integer.parseInt(arr2[0]);
        int m2 = Integer.parseInt(arr2[1]);
        int s1 = 0, s2 = 0;
        if (arr1.length == 3) {
            s1 = Integer.parseInt(arr1[2]);
        }
        if (arr2.length == 3) {
            s2 = Integer.parseInt(arr2[2]);
        }
        if (h1 < 0 || h1 > 23 || m1 < 0 || m1 > 59 || s1 < 0 || s1 > 59) {
            throw new RuntimeException("错误的时间值:" + time1);
        }
        if (h2 < 0 || h2 > 23 || m2 < 0 || m2 > 59 || s2 < 0 || s2 > 59) {
            throw new RuntimeException("错误的时间值:" + time2);
        }
        if (h1 != h2) {
            return h1 > h2 ? 1 : -1;
        } else {
            if (m1 == m2) {
                if (s1 == s2) {
                    return 0;
                } else {
                    return s1 > s2 ? 1 : -1;
                }
            } else {
                return m1 > m2 ? 1 : -1;
            }
        }
    }

    public static boolean isTime(String time) {
        String[] arr = time.split(":");
        if (arr.length < 2) {
            return false;
        }
        try {
            int h = Integer.parseInt(arr[0]);
            int m = Integer.parseInt(arr[1]);
            int s = 0;
            if (arr.length == 3) {
                s = Integer.parseInt(arr[2]);
            }
            if (h < 0 || h > 23 || m < 0 || m > 59 || s < 0 || s > 59) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean isDate(String date) {
        String[] arr = date.split("-");
        if (arr.length < 3) {
            return false;
        }
        try {
            int y = Integer.parseInt(arr[0]);
            int m = Integer.parseInt(arr[1]);
            int d = Integer.parseInt(arr[2]);
            if (y < 0 || m > 12 || m < 0 || d < 0 || d > 31) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean isWeekend(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int t = cal.get(Calendar.DAY_OF_WEEK);
        if (t == Calendar.SATURDAY || t == Calendar.SUNDAY) {
            return true;
        }
        return false;
    }

    public static boolean isWeekend(String str) {
        return isWeekend(parse(str));
    }

    public static Date parse(String str) {
        if (StringUtil.isEmpty(str)) {
            return null;
        }
        try {
            return SDFD.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date parse(String str, String format) {
        if (StringUtil.isEmpty(str)) {
            return null;
        }
        try {
            SimpleDateFormat t = new SimpleDateFormat(format);
            return t.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date parseDateTime(String str) {
        if (StringUtil.isEmpty(str)) {
            return null;
        }
        if (str.length() == 10) {
            return parse(str);
        }
        try {
            return SDFDT.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date parseDateTime(String str, String format) {
        if (StringUtil.isEmpty(str)) {
            return null;
        }
        try {
            SimpleDateFormat t = new SimpleDateFormat(format);
            return t.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 格式化时间，按照yyyy-MM-dd HH:mm:ss格式
     * 
     * @param date
     *            时间
     * 
     * @return String
     */
    public static String formatDateTime(Date date) {
        if (date == null) {
            return "";
        }
        return SDFDT.format(date);
    }

    /**
     * 格式化时间，按照yyyy-MM-dd格式
     * 
     * @param date
     *            时间
     * 
     * @return String
     */
    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        return SDFD.format(date);
    }

    /**
     * 日期date上加count分钟，count为负表示减
     */
    public static Date addMinute(Date date, int count) {
        return new Date(date.getTime() + 60000L * count);
    }

    /**
     * 日期date上加count小时，count为负表示减
     */
    public static Date addHour(Date date, int count) {
        return new Date(date.getTime() + 3600000L * count);
    }

    /**
     * 日期date上加count天，count为负表示减
     */
    public static Date addDay(Date date, int count) {
        return new Date(date.getTime() + 86400000L * count);
    }

    /**
     * 日期date上加count星期，count为负表示减
     */
    public static Date addWeek(Date date, int count) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.WEEK_OF_YEAR, count);
        return c.getTime();
    }

    /**
     * 日期date上加count月，count为负表示减
     */
    public static Date addMonth(Date date, int count) {
        /* ${_ZVING_LICENSE_CODE_} */

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, count);
        return c.getTime();
    }

    /**
     * 日期date上加count年，count为负表示减
     */
    public static Date addYear(Date date, int count) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, count);
        return c.getTime();
    }

    /**
     * 根据一个日期，返回是星期几的字符串
     * 
     * @param date
     * @return
     */
    public static String getWeek(String date) {
        String[] weeks = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(parse(date));
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        return weeks[week_index];
    }

    /**
     * 获取一周列表
     * 
     * @return List
     * 
     */
    public static List<Map<String, String>> getWeekdayList() {
        String[] weekday = { "周一", "周二", "周三", "周四", "周五", "周六", "周日" };
        List<Map<String, String>> weekdayList = new ArrayList<Map<String, String>>();
        for (int i = 0; i < weekday.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("name", weekday[i]);
            map.put("value", String.valueOf(i + 1));
            map.put("checked", "");
            weekdayList.add(map);
        }
        return weekdayList;
    }

    /**
     * 日期返回星期几
     * 
     * @param date
     * 
     * @return int
     */
    public static int dayOfWeek(String date) {
        try {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            c.setTime(dateFormat.parse(date));
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
            return dayOfWeek == 0 ? 7 : dayOfWeek;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 取得当前日期是多少周
     * 
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);

        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 得到某年某周的第一天
     * 
     * @param year
     * @param week
     * @return
     */
    public static String getFirstDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, week * 7);

        return formatDate(getFirstDayOfWeek(cal.getTime()));
    }

    /**
     * 得到某年某周的最后一天
     * 
     * @param year
     * @param week
     * @return
     */
    public static String getLastDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, week * 7);

        return formatDate(getLastDayOfWeek(cal.getTime()));
    }

    /**
     * 取得当前日期所在周的第一天
     * 
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        return c.getTime();
    }

    /**
     * 取得当前日期所在周的最后一天
     * 
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
        return c.getTime();
    }

    /**
     * 取得当前月份的第一天
     * 
     * @param date
     * @return
     */
    public static String getFirstDayOfMonth(Date date) {
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return formatDate(c.getTime());
    }

    /**
     * 获取N天前的日期
     * 
     * @param date
     *            当前日期 num 几天前
     * @return
     * */
    public static Date getBeginData(Date date, int num) {
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.add(Calendar.DATE, -num);
        return c.getTime();
    }

    public static int getBetweenDays(Date d1, Date nextD) {
        Calendar c1 = new GregorianCalendar();
        c1.setTime(d1);
        Calendar c2 = new GregorianCalendar();
        c2.setTime(nextD);
        return c1.get(Calendar.DAY_OF_YEAR) - c2.get(Calendar.DAY_OF_YEAR);
    }

    public static int getBetweenMinutes(Date d1, Date nextD) {
        Calendar c1 = new GregorianCalendar();
        c1.setTime(d1);
        Calendar c2 = new GregorianCalendar();
        c2.setTime(nextD);
        return (int) ((c1.getTimeInMillis() - c2.getTimeInMillis()) / (1000 * 60));
    }

    /**
     * 获取当前年
     * 
     * @return
     */
    public static int getCurrentYear() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        return year;
    }

    /**
     * 获取当前月
     * 
     * @return
     */
    public static int getCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        return month;
    }
}
