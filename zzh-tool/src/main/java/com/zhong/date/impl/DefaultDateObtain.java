package com.zhong.date.impl;


import com.zhong.date.AbstractDate;
import com.zhong.date.DateWrapper;
import com.zhong.date.IDate;
import com.zhong.date.IDateObtain;

import java.util.Calendar;
import java.util.Date;

/**
 * @author ashui
 * @date 2021/9/8
 */
public class DefaultDateObtain extends AbstractDate implements IDateObtain {

    public DefaultDateObtain(DateWrapper warpper) {
        super(warpper);
    }

    /**
     * 当前天0点
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    @Override
    public IDate startOfDay() {
        Date date = wrapper.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        wrapper.update(calendar.getTime());
        return null;
    }

    /**
     * 当前天23点59分59
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    @Override
    public IDate endOfDay() {
        Date date = wrapper.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        wrapper.update(calendar.getTime());
        return null;
    }


    /**
     * 获取当前月第一天
     *
     * @author hengyu
     * @date 2021/9/7
     **/
    @Override
    public IDate firstDayOfMonth() {
        Date date = wrapper.getDate();
        //获取当前月第一天：
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        wrapper.update(calendar.getTime());
        return null;
    }

    /**
     * 获取当前月最后一天
     *
     * @author hengyu
     * @date 2021/9/7
     **/
    @Override
    public IDate lastDayOfMonth() {
        Date date = wrapper.getDate();
        //获取当前月最后一天
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        wrapper.update(calendar.getTime());
        return null;
    }

    /**
     * 获取当前年第一天
     *
     * @author hengyu
     * @date 2021/9/7
     **/
    @Override
    public IDate firstDayOfYear() {
        Date date = wrapper.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        wrapper.update(calendar.getTime());
        return null;
    }

    /**
     * 获取当前年最后一天
     *
     * @author hengyu
     * @date 2021/9/7
     **/
    @Override
    public IDate lastDayOfYear() {
        Date date = wrapper.getDate();
        //获取当前年最后一天
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        wrapper.update(calendar.getTime());
        return null;
    }

    /**
     * 获取当前周第一天
     *
     * @author hengyu
     * @date 2021/9/7
     **/
    @Override
    public IDate firstDayOfWeek() {
        Date date = wrapper.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        wrapper.update(calendar.getTime());
        return null;
    }

    /**
     * 获取当前周最后一天
     *
     * @author hengyu
     * @date 2021/9/7
     **/
    @Override
    public IDate lastDayOfWeek() {
        Date date = wrapper.getDate();
        //获取当前周最后一天
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek() + 6);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        wrapper.update(calendar.getTime());
        return null;
    }

    /**
     * 返回年份
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    @Override
    public Integer year() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(wrapper.getDate());
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 返回月
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    @Override
    public Integer month() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(wrapper.getDate());
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 返回日
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    @Override
    public Integer day() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(wrapper.getDate());
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 返回小时
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    @Override
    public Integer hour() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(wrapper.getDate());
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 返回分钟
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    @Override
    public Integer minute() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(wrapper.getDate());
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 返回秒
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    @Override
    public Integer second() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(wrapper.getDate());
        return calendar.get(Calendar.SECOND);
    }
}
