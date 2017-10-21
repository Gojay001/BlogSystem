package cn.gojay.encode;

import java.util.Calendar;

/**
 * 时间工具类
 * 转换日期及处理时间显示问题
 */
public class MyDate {

    /**
     * 通过Date按点格式显示日期
     * @param date java.util.Date类
     * @return String 点格式日期
     */
    public static String showDateByPoint(java.util.Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String year = String.valueOf(calendar.get(Calendar.YEAR)).substring(2);
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        if (Integer.parseInt(month) < 10) {
            month = 0 + month;
        }
        int day = calendar.get(Calendar.DATE);

        return year + "." + month + "." + day;
    }

    /**
     * 通过Date按横线格式显示日期
     * @param date java.sql.Date类
     * @return String 横线格式日期
     */
    public static String showDateByLine(java.sql.Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        if (Integer.parseInt(month) < 10) {
            month = 0 + month;
        }

        return calendar.get(Calendar.YEAR) + "-" + month + "-" + calendar.get(Calendar.DATE);
    }

    /**
     * 通过年月日返回java.sql.Date
     * @param year 年
     * @param month 月
     * @param day 日
     * @return java.sql.Date
     */
    public static java.sql.Date getSqlDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, day);
        Long time = calendar.getTimeInMillis();
        java.sql.Date date = new java.sql.Date(time);

        return date;
    }
}
