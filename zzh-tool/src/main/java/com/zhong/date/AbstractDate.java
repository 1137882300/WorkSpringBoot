package com.zhong.date;

import java.util.Calendar;
import java.util.Date;

/**
 * @author ashui
 * @date 2021/9/8
 */
public abstract class AbstractDate {

    protected DateWrapper wrapper;

    public AbstractDate(DateWrapper wrapper) {
        this.wrapper = wrapper;
    }


    /**
     * 日期增减
     *
     * @author hengyu
     * @date 2021/8/19
     **/
    protected IDate add(int calendarField, int num) {
        Date date = wrapper.getDate();
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, num);
        date = c.getTime();
        this.wrapper.update(date);
        return null;
    }
}
