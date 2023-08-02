package com.zhong.date.impl;


import com.zhong.date.AbstractDate;
import com.zhong.date.DateWrapper;
import com.zhong.date.IDate;
import com.zhong.date.IDateOperation;

import java.util.Calendar;

/**
 * @author ashui
 * @date 2021/9/8
 */
public class DefaultDateOperation extends AbstractDate implements IDateOperation {


    public DefaultDateOperation(DateWrapper warpper) {
        super(warpper);
    }

    /**
     * 日期增减年
     *
     * @author hengyu
     * @date 2021/8/19
     **/
    @Override
    public IDate addYears(int num) {
        return add(Calendar.YEAR, num);
    }

    /**
     * 日期增减月份
     *
     * @author hengyu
     * @date 2021/8/19
     **/
    @Override
    public IDate addMonths(int num) {
        return add(Calendar.MONTH, num);
    }

    /**
     * 日期增减周
     *
     * @author hengyu
     * @date 2021/8/19
     **/
    @Override
    public IDate addWeeks(int num) {
        return add(Calendar.WEEK_OF_YEAR, num);
    }

    /**
     * 日期增减天
     *
     * @author hengyu
     * @date 2021/8/19
     **/
    @Override
    public IDate addDays(int num) {
        return add(Calendar.DAY_OF_MONTH, num);
    }

    /**
     * 日期增减小时
     *
     * @author hengyu
     * @date 2021/8/19
     **/
    @Override
    public IDate addHours(int num) {
        return add(Calendar.HOUR_OF_DAY, num);
    }

    /**
     * 日期增减分
     *
     * @author hengyu
     * @date 2021/8/19
     **/
    @Override
    public IDate addMinutes(int num) {
        return add(Calendar.MINUTE, num);
    }

    /**
     * 日期增减秒
     *
     * @author hengyu
     * @date 2021/8/19
     **/
    @Override
    public IDate addSeconds(int num) {
        return add(Calendar.SECOND, num);
    }

    /**
     * 日期增减毫秒
     *
     * @author hengyu
     * @date 2021/8/19
     **/
    @Override
    public IDate addMilliseconds(int num) {
        return add(Calendar.MILLISECOND, num);
    }


}
