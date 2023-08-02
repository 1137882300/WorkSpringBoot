package com.zhong.date.impl;


import com.zhong.date.IDateParse;
import com.zhong.date.enums.DateFormatEnum;
import com.zhong.date.exception.WLDateException;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ashui
 * @date 2021/9/8
 */
public class DefaultDateParse implements IDateParse {


   /**
    * 指定格式解析日期
    * @author ashui
    * @date 2022/5/11
    */
    @Override
    public Date parse(String datetime, DateFormatEnum dateFormatEnum) {
        return strToDate(datetime, dateFormatEnum.value);
    }

    /**
     * yyyy-MM-dd hh:mm:ss字符串转时间
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    @Override
    public Date parseDateTimeString(String datetime) {
        return strToDate(datetime, DateFormatEnum.YYYY_MM_DD_HH_MM_SS.value);
    }

    /**
     * yyyy-MM-dd字符串转时间  edge eagle  鹰
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    @Override
    public Date parseDateString(String date) {
        return strToDate(date, DateFormatEnum.YYYY_MM_DD.value);
    }

    /**
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second 设置年
     * @author hengyu
     * @date 2021/9/6
     */
    @Override
    public Date parse(int year, int month, int day, int hour, int minute, int second) {
        return ofSet(year, month, day, hour, minute, second);
    }

    /**
     * @param year
     * @param month
     * @param day   设置年
     * @author hengyu
     * @date 2021/9/6
     */
    @Override
    public Date parse(int year, int month, int day) {
        return ofSet(year, month, day, 0, 0, 0);
    }

    /**
     * @param years
     * @param month
     * @param day   设置年
     * @author hengyu
     * @date 2021/9/6
     */
    private Date ofSet(int years, int month, int day, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, years);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 字符串转时间
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    private Date strToDate(String str, String fmt) {
        if (StringUtils.isBlank(str) || StringUtils.isBlank(fmt)) {
            WLDateException.throwException("日期, 格式不能为空");
        }
        SimpleDateFormat format = new SimpleDateFormat(fmt);
        Date dateTemp = null;
        try {
            dateTemp = format.parse(str);
        } catch (Exception e) {
            WLDateException.throwException("日期与格式不符");
        }
        return dateTemp;
    }

    /**
     * 10,13位毫秒时间戳转Date
     *
     * @param timestamp
     * @author hengyu
     * @date 2021/9/7
     */
    @Override
    public Date parse(Long timestamp) {
        int length = timestamp.toString().length();
        if (length < 8) {
            WLDateException.throwException("时间戳长度必须大于等于8位");
        }
        if (length < 11) {
            timestamp = timestamp * 1000;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        return calendar.getTime();
    }
}
