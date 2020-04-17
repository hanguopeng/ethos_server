package com.dutyMS.common.utils;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Mela.S
 * @date 2019/12/25
 */
public class DutyUtils {
    /**
     * 将字符串转化为Long
     * @param str
     * @return
     */
    public static Long parseLong(String str){
        if (StringUtils.isEmpty(str)){
            return null;
        }else{
            return Long.parseLong(str);
        }
    }
    /**
     * 将字符串转化为Integer
     * @param str
     * @return
     */
    public static Integer parseInteger(String str){
        if (StringUtils.isEmpty(str)){
            return null;
        }else{
            return Integer.parseInt(str);
        }
    }
    /**
     * 比较给定时间是否比当前时间早
     * 比较班次日期（用于微调，替换班班次限制）
     */
    public static boolean checkNowBeforeAppointTime(Date appointTime){
        Date now = new Date();
        if (now.getTime()<appointTime.getTime()) return true;
        return false;
    }

    /**
     * 比较给定时间是否比当前时间晚
     */
    public static boolean checkNowAfterAppointTime(Date appointTime){
        Date now = new Date();
        if (now.getTime()>appointTime.getTime()) return true;
        return false;
    }

    /**
     *  判断时间区间是否跨天
     */
    public static boolean timeAreaAcrossDay(String timeArea){
        String[] timeList = timeArea.split("-");
        if (Integer.parseInt(timeList[0].substring(0,2))>Integer.parseInt(timeList[1].substring(0,2))) return true;
        return false;
    }

    /**
     *  给定时间加N天
     */
    public static Date dateAddNDay(Date date, Integer number){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,number);
        return calendar.getTime();
    }
}
