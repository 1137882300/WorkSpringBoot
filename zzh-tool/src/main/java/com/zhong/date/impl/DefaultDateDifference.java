package com.zhong.date.impl;


import com.zhong.date.AbstractDate;
import com.zhong.date.DateWrapper;
import com.zhong.date.IDate;
import com.zhong.date.IDateDifference;
import com.zhong.date.exception.WLDateException;

import java.util.Calendar;
import java.util.Date;

/**
 * @author ashui
 * @date 2021/9/8
 */
public class DefaultDateDifference extends AbstractDate implements IDateDifference {

    public DefaultDateDifference(DateWrapper warpper) {
        super(warpper);
    }

    /**
     * 计算两个日期相差年数
     *
     * @param endDate
     * @author hengyu
     * @date 2021/8/18
     */
    @Override
    public int diffInYears(Date endDate) {
        if (endDate == null) {
            WLDateException.throwException("日期不能为空");
        }
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(wrapper.getDate());
        calendar2.setTime(endDate);
        int year = (calendar2.get(Calendar.YEAR) - calendar1.get(Calendar.YEAR));
        return year;
    }

    /**
     * 计算两个日期相差年数
     *
     * @param endIDate
     * @author hengyu
     * @date 2021/8/18
     */
    @Override
    public int diffInYears(IDate endIDate) {
        return diffInYears(endIDate.toDate());
    }

    /**
     * 计算两个日期相差月数
     *
     * @param endDate
     * @author hengyu
     * @date 2021/8/18
     */
    @Override
    public int diffInMonths(Date endDate) {
        if (endDate == null) {
            WLDateException.throwException("日期不能为空");
        }
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(wrapper.getDate());
        calendar2.setTime(endDate);
        int result = calendar2.get(Calendar.MONTH) - calendar1.get(Calendar.MONTH);
        int month = (calendar2.get(Calendar.YEAR) - calendar1.get(Calendar.YEAR)) * 12;
        return month + result;
    }

    /**
     * 计算两个日期相差月数
     *
     * @param endIDate
     * @author hengyu
     * @date 2021/8/18
     */
    @Override
    public int diffInMonths(IDate endIDate) {
        return diffInMonths(endIDate.toDate());
    }

    /**
     * 计算两个日期相差天数
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    @Override
    public int diffInDays(Date endDate) {
        if (endDate == null) {
            WLDateException.throwException("日期不能为空");
        }
        return (int) ((endDate.getTime() - wrapper.getDate().getTime()) / (24 * 3600 * 1000));
    }

    /**
     * 计算两个日期相差天数
     *
     * @param endIDate
     * @author hengyu
     * @date 2021/8/18
     */
    @Override
    public int diffInDays(IDate endIDate) {
        return diffInDays(endIDate.toDate());
    }

    /**
     * 计算两个日期相差时
     *
     * @param endDate
     * @author hengyu
     * @date 2021/8/18
     */
    @Override
    public int diffInHours(Date endDate) {
        if (endDate == null) {
            WLDateException.throwException("日期不能为空");
        }
        return (int) ((endDate.getTime() - wrapper.getDate().getTime()) / (3600 * 1000));
    }

    /**
     * 计算两个日期相差时
     *
     * @param endIDate
     * @author hengyu
     * @date 2021/8/18
     */
    @Override
    public int diffInHours(IDate endIDate) {
        return diffInHours(endIDate.toDate());
    }

    /**
     * 计算两个日期相差分
     *
     * @param endDate
     * @author hengyu
     * @date 2021/8/18
     */
    @Override
    public int diffInMinutes(Date endDate) {
        if (endDate == null) {
            WLDateException.throwException("日期不能为空");
        }
        return (int) ((endDate.getTime() - wrapper.getDate().getTime()) / (60 * 1000));
    }

    /**
     * 计算两个日期相差分
     *
     * @param endIDate
     * @author hengyu
     * @date 2021/8/18
     */
    @Override
    public int diffInMinutes(IDate endIDate) {
        return diffInMinutes(endIDate.toDate());
    }

    /**
     * 计算两个日期相差秒
     *
     * @param endDate
     * @author hengyu
     * @date 2021/8/18
     */
    @Override
    public int diffInSeconds(Date endDate) {
        if (endDate == null) {
            WLDateException.throwException("日期不能为空");
        }
        return (int) ((endDate.getTime() - wrapper.getDate().getTime()) / 1000);
    }

    /**
     * 计算两个日期相差秒
     *
     * @param endIDate
     * @author hengyu
     * @date 2021/8/18
     */
    @Override
    public int diffInSeconds(IDate endIDate) {
        return diffInSeconds(endIDate.toDate());
    }
}
