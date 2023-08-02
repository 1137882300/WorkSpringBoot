package com.zhong.date.impl;


import com.zhong.date.AbstractDate;
import com.zhong.date.DateWrapper;
import com.zhong.date.IDateWeek;
import com.zhong.date.enums.DateWeekEnum;

import java.util.Calendar;

/**
 * @author hengyu
 * @date 2021/09/09
 */
public class DefaultDateWeek extends AbstractDate implements IDateWeek {

    public DefaultDateWeek(DateWrapper warpper) {
        super(warpper);
    }

    /**
     * 返回周几
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    @Override
    public String weekDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(wrapper.getDate());
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return getWeekStrWithEnum(DateWeekEnum.WEEK, dayOfWeek);
    }

    /**
     * 根据枚举返回周几格式
     * see {@link DateWeekEnum}
     *
     * @param dateWeekEnum
     * @author hengyu
     * @date 2021/9/9
     */
    @Override
    public String weekDay(DateWeekEnum dateWeekEnum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(wrapper.getDate());
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return getWeekStrWithEnum(dateWeekEnum, dayOfWeek);
    }

    /**
     * @author: 竹阳
     * @date: 2022/11/17
     * 返回星期几数字
     */
    @Override
    public Integer weekDayNum() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(wrapper.getDate());
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case 1:
                return 7;
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            case 5:
                return 4;
            case 6:
                return 5;
            case 7:
                return 6;
        }
        return 0;
    }

    /**
     * 返回周几
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    private String getWeekStrWithEnum(DateWeekEnum dateWeekEnum, int dayOfWeek) {
        switch (dateWeekEnum) {
            case EN:
                return getEnStr(dayOfWeek);
            case CHOU:
                return getChouStr(dayOfWeek);
            case WEEK:
                return getWeekStr(dayOfWeek);
            default:
                return "";
        }
    }

    /**
     * 解析周几
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    private String getChouStr(int dayOfWeek) {
        switch (dayOfWeek) {
            case 1:
                return "周日";
            case 2:
                return "周一";
            case 3:
                return "周二";
            case 4:
                return "周三";
            case 5:
                return "周四";
            case 6:
                return "周五";
            case 7:
                return "周六";
            default:
                return "";
        }
    }

    /**
     * 解析周几
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    private String getWeekStr(int weekDay) {
        switch (weekDay) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
            default:
                return "";
        }
    }

    /**
     * 解析周几
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    private String getEnStr(int weekDay) {
        switch (weekDay) {
            case 1:
                return "SUNDAY";
            case 2:
                return "MONDAY";
            case 3:
                return "TUESDAY";
            case 4:
                return "WEDNESDAY";
            case 5:
                return "THURSDAY";
            case 6:
                return "FRIDAY";
            case 7:
                return "SATURDAY";
            default:
                return "";
        }
    }
}
