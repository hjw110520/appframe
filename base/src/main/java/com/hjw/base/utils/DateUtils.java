package com.hjw.base.utils;


import android.support.annotation.Nullable;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by jinwei on 2015/12/18.
 */
public class DateUtils {
    public static final String FMT_DD_NOTE = "yyyy.MM.dd"; //2016.2.12
    public static final String FMT_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String FMT_SS_S = "yyyy-MM-ddHH:mm:ss";
    public static final String FMT_MM = "yyyy-MM-dd HH:mm";
    public static final String FMT_DD = "yyyy-MM-dd";
    public static final String FMT_STR="yyyy年MM月dd日";
    public static final String FMT_TSS = "HH:mm:ss";
    /** 日期/时间格式  "yyyy-MM-dd HH:mm:ss,SSS" */
    public static final SimpleDateFormat DATETIME_FULL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
    /** 日期/时间格式  "yyyyMMddHHmmssSSS" */
    public static final SimpleDateFormat DATETIME_FULL_STR = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    /** 日期/时间格式  "yyyy-MM-dd HH:mm:ss" */
    public static final SimpleDateFormat DATETIME_SEC = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /** 日期/时间格式  "yyyyMMddHHmmss" */
    public static final SimpleDateFormat DATETIME_SEC_STR = new SimpleDateFormat("yyyyMMddHHmmss");
    /** 日期/时间格式  "yyyy-MM-dd" */
    public static final SimpleDateFormat DATE = new SimpleDateFormat("yyyy-MM-dd");
    /** 日期/时间格式  "yyyy" */
    public static final SimpleDateFormat DATE_YEAR = new SimpleDateFormat("yyyy");
    /** 日期/时间格式  "yyyyMM" */
    public static final SimpleDateFormat DATE_YEAR_MONTH = new SimpleDateFormat("yyyyMM");
    /** 日期/时间格式  "yyyyMMdd" */
    public static final SimpleDateFormat DATE_STR = new SimpleDateFormat("yyyyMMdd");
    /** 日期/时间格式  "yyMMdd" */
    public static final SimpleDateFormat DATE_SHORT_STR = new SimpleDateFormat("yyMMdd");
    /** 日期/时间格式  "yyMMdd" */
    public static final SimpleDateFormat DATE_SHORT_YEAR_MONTH = new SimpleDateFormat("yyMM");
    /** 日期/时间格式  "MMdd" */
    public static final SimpleDateFormat DATE_NO_YEAR = new SimpleDateFormat("MMdd");
    /** 获得月份  "MM" */
    public static final SimpleDateFormat DATE_NO_MONTH = new SimpleDateFormat("MM");
    /** 日期/时间格式  "HHmmss" */
    public static final SimpleDateFormat TIME_SEC_STR = new SimpleDateFormat("HHmmss");
    /** 日期/时间格式  "HH:mm:ss" */
    public static final SimpleDateFormat TIME_SEC = new SimpleDateFormat("HH:mm:ss");
    /** 日期/时间格式  "HH:mm" */
    public static final SimpleDateFormat TIME_MIN = new SimpleDateFormat("HH:mm");

    public static final String MIN_DATE = "1970-01-01 00:00:00";

    public static Timestamp stringToTimestamp(String date) throws Exception {
        return  new Timestamp(stringToDate(date,FMT_DD).getTime());
    }

    public static String timestampToString(Timestamp date, String fmt) throws Exception {
        if(date==null){ return  "";}
        return  new SimpleDateFormat(fmt).format(date);
    }


    @Nullable
    public static Date stringToDate(String str, String fmt) {
        try {
            DateFormat format = new SimpleDateFormat(fmt);
            return format.parse(str);
        }catch (Exception e){
            return null;
        }
    }

    public static String dateToString(Date date, String fmt){
        try {
            if(null!=date) {
                DateFormat format = new SimpleDateFormat(fmt);
                return format.format(date);
            }else{
                return "";
            }
        }catch (Exception e){
            return "";
        }
    }

    public static String timeStrToTodayStr(String time) throws Exception {
        return dateToString(new Date(),FMT_DD)+" "+time;
    }

    public static Date timeStrToToday(String time) throws Exception {
        return stringToDate(dateToString(new Date(),FMT_DD)+" "+time,FMT_SS);
    }

    /**
     *
     * @param time
     * @param afterDay
     * @return
     * @throws Exception
     */
    public static Date timeStrToDate(String time, int afterDay) throws Exception {
        Date today = stringToDate(dateToString(new Date(),FMT_DD)+" "+time,FMT_SS);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(today);
        calendar.add(calendar.DATE,afterDay);
        return calendar.getTime();
    }

    /**
     * 加天数
     * @param date 日期
     * @param days 天数
     * @return
     * @throws Exception
     */
    public static Date addDays(Date date, int days) throws Exception {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,days);
        return calendar.getTime();
    }


    public static String dateStrToString(String date, String fmt){
        return dateToString(stringToDate(date,fmt), fmt);
    }

    /**
     * 计算两个日期的时间差
     * @param formatTime1
     * @param formatTime2
     * @return
     */
    public static int getTimeDifference(Timestamp formatTime1, Timestamp formatTime2)throws Exception {
        SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");
        long t1 = formatTime1.getTime();
        long t2 = formatTime2.getTime();
        //因为t1-t2得到的是毫秒级,所以要初3600000*24得出天数
        int days=(int) ((t1 - t2)/86400000);
        return days;
    }

    private static int compareDateStr(String date1, String date2) {
        DateFormat df = new SimpleDateFormat(FMT_SS);
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            return compareDate(dt1,dt2);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    private  static int compareDate(Date date1, Date date2) {
        try {
            if (date1.getTime() > date2.getTime()) {
                return 1;
            } else if (date1.getTime() < date2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static boolean moreThan(Date date1, Date date2) {
        if(compareDate(date1,date2) == 1){
            return true;
        }
        return false;
    }

    public static boolean lessThan(Date date1, Date date2) {
        if(compareDate(date1,date2) == -1){
            return true;
        }
        return false;
    }
    public static boolean equals(Date date1, Date date2) {
        if(compareDate(date1,date2) == 0){
            return true;
        }
        return false;
    }

    public static boolean moreThan(String date1, String date2) {
        if(compareDateStr(date1,date2) == 1){
            return true;
        }
        return false;
    }

    public static boolean lessThan(String date1, String date2) {
        if(compareDateStr(date1,date2) == -1){
            return true;
        }
        return false;
    }
    public static boolean equals(String date1, String date2) {
        if(compareDateStr(date1,date2) == 0){
            return true;
        }
        return false;
    }

    /**
     * 获取月份起始日期
     * @param date
     * @return
     * @throws
     */
    public static String getMinMonthDate(Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return DATE.format(calendar.getTime());
    }

    /**
     * 获取月份最后日期
     * @param date
     * @return
     * @throws
     */
    public static String getMaxMonthDate(Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return DATE.format(calendar.getTime());
    }

    public static String getDateBeforByMoth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        return DATE.format(calendar.getTime());
    }

    public static String getDateBeforByWeek(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.WEEK_OF_YEAR, -1);
        return DATE.format(calendar.getTime());
    }

    public static String getDateBeforByWeek(Date date, SimpleDateFormat simpleDateFormat){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.WEEK_OF_YEAR, -1);
        return simpleDateFormat.format(calendar.getTime());
    }

    /**
     * 获取当天的9点
     * @return
     * @throws
     */
    public static Timestamp getNine() throws Exception {
        return new Timestamp(DateUtils.stringToDate(DateUtils.dateToString(new Date(),DateUtils.FMT_DD)+" 09:00:00",DateUtils.FMT_SS).getTime());
    }
/**
     * 获取当天的0点
     * @return
     * @throws
     */
    public static Timestamp getZero() throws Exception {
        return new Timestamp(DateUtils.stringToDate(DateUtils.dateToString(new Date(),DateUtils.FMT_DD)+" 00:00:00",DateUtils.FMT_SS).getTime());
    }

    /**
     * 获取某天的0点
     * @return
     * @throws
     */
    public static Timestamp getDayZero(Date date) throws Exception {
        return new Timestamp(DateUtils.stringToDate(DateUtils.dateToString(date,DateUtils.FMT_DD)+" 00:00:00",DateUtils.FMT_SS).getTime());
    }
}

