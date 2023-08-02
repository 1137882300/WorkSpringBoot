package com.zhong.date.impl;


import com.zhong.date.AbstractDate;
import com.zhong.date.DateWrapper;
import com.zhong.date.IDate;
import com.zhong.date.IDateCompare;
import com.zhong.date.exception.WLDateException;

import java.util.Calendar;
import java.util.Date;

/**
 * @author ashui
 * @date 2021/9/8
 */
public class DefaultDateCompare extends AbstractDate implements IDateCompare {

    public DefaultDateCompare(DateWrapper warpper) {
        super(warpper);
    }

    /**
     * 是否同一年
     *
     * @param date
     * @author hengyu
     * @date 2021/9/9
     */
    @Override
    public boolean isSameYear(Date date) {
        if (date == null) {
            WLDateException.throwException("日期不能为空");
        }
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(wrapper.getDate());
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date);
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR);
    }

    /**
     * 是否同一年
     *
     * @param iDate
     * @author hengyu
     * @date 2021/9/9
     */
    @Override
    public boolean isSameYear(IDate iDate) {
        return isSameYear(iDate.toDate());
    }

    /**
     * 同年同月
     *
     * @param date
     * @author hengyu
     * @date 2021/9/9
     */
    @Override
    public boolean isSameYearAndMonth(Date date) {
        if (date == null) {
            WLDateException.throwException("日期不能为空");
        }
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(wrapper.getDate());
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date);
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
                && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH);
    }

    /**
     * 同年同月
     *
     * @param iDate
     * @author hengyu
     * @date 2021/9/9
     */
    @Override
    public boolean isSameYearAndMonth(IDate iDate) {
        return isSameYearAndMonth(iDate.toDate());
    }

    /**
     * 同月同日
     *
     * @param date
     * @author hengyu
     * @date 2021/9/9
     */
    @Override
    public boolean isSameMonthAndDay(Date date) {
        if (date == null) {
            WLDateException.throwException("日期不能为空");
        }
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(wrapper.getDate());
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date);
        return calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
                && calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 同月同日
     *
     * @param iDate
     * @author hengyu
     * @date 2021/9/9
     */
    @Override
    public boolean isSameMonthAndDay(IDate iDate) {
        return isSameMonthAndDay(iDate.toDate());
    }

    /**
     * 是否是今年
     *
     * @author hengyu
     * @date 2021/9/9
     */
    @Override
    public boolean isCurrentYear() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(wrapper.getDate());
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(new Date());
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR);
    }

    /**
     * 是否为今天
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    @Override
    public boolean isToday() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(wrapper.getDate());
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(new Date());
        return calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
                && calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH)
                && calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR);
    }

    /**
     * 是否为昨天
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    @Override
    public boolean isYesterday() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(wrapper.getDate());
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(new Date());
        return calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
                && (calendar1.get(Calendar.DAY_OF_MONTH) + 1) == calendar2.get(Calendar.DAY_OF_MONTH)
                && calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR);
    }

    /**
     * 是否为明天
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    @Override
    public boolean isTomorrow() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(wrapper.getDate());
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(new Date());
        return calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
                && (calendar1.get(Calendar.DAY_OF_MONTH) - 1) == calendar2.get(Calendar.DAY_OF_MONTH)
                && calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR);
    }

    /**
     * 检查是否为00:00:00
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    @Override
    public boolean isStartOfDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(wrapper.getDate());
        return calendar.get(Calendar.HOUR_OF_DAY) == 0 && calendar.get(Calendar.MINUTE) == 0 && calendar.get(Calendar.SECOND) == 0;
    }

    /**
     * 检查是是否为23:59:59
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    @Override
    public boolean isEndOfDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(wrapper.getDate());
        return calendar.get(Calendar.HOUR_OF_DAY) == 23 && calendar.get(Calendar.MINUTE) == 59 && calendar.get(Calendar.SECOND) == 59;
    }

    /**
     * @param date 比较日期大小
     * @author hengyu
     * @date 2021/9/6
     */
    @Override
    public boolean before(Date date) {
        if (date == null) {
            WLDateException.throwException("日期不能为空");
        }
        return wrapper.getDate().compareTo(date) < 0;
    }

    /**
     * 比较日期大小
     *
     * @param iDate
     * @author hengyu
     * @date 2021/9/6
     */
    @Override
    public boolean before(IDate iDate) {
        return wrapper.getDate().compareTo(iDate.toDate()) < 0;
    }

    /**
     * 比较日期大小
     *
     * @param date
     * @author hengyu
     * @date 2021/9/6
     */
    @Override
    public boolean after(Date date) {
        return wrapper.getDate().compareTo(date) > 0;
    }

    /**
     * 比较日期大小
     *
     * @param iDate
     * @author hengyu
     * @date 2021/9/6
     */
    @Override
    public boolean after(IDate iDate) {
        return wrapper.getDate().compareTo(iDate.toDate()) > 0;
    }

    /**
     * 是否同一天
     *
     * @author hengyu
     * @date 2021/8/19
     **/
    @Override
    public boolean isSameDay(Date date) {
        if (date == null) {
            WLDateException.throwException("日期不能为空");
        }

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(wrapper.getDate());
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date);
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
                && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
                && calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 是否同一天
     *
     * @param iDate
     * @author hengyu
     * @date 2021/8/19
     */
    @Override
    public boolean isSameDay(IDate iDate) {
        return isSameDay(iDate.toDate());
    }

}
