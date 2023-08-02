package com.zhong.date;

import java.util.Date;

/**
 * 时间比较
 *
 * @author hengyu
 * @date 2021/09/07
 */
public interface IDateCompare {

    /**
     * 比较日期大小
     *
     * @author hengyu
     * @date 2021/9/6
     **/
    boolean before(Date date);

    /**
     * 比较日期大小
     *
     * @author hengyu
     * @date 2021/9/6
     **/
    boolean before(IDate iDate);

    /**
     * 比较日期大小
     *
     * @author hengyu
     * @date 2021/9/6
     **/
    boolean after(Date date);

    /**
     * 比较日期大小
     *
     * @author hengyu
     * @date 2021/9/6
     **/
    boolean after(IDate iDate);

    /**
     * 是否同一天
     *
     * @author hengyu
     * @date 2021/8/19
     **/
    boolean isSameDay(Date date);

    /**
     * 是否同一天
     *
     * @author hengyu
     * @date 2021/8/19
     **/
    boolean isSameDay(IDate iDate);

    /**
     * 是否同一年
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    boolean isSameYear(Date date);

    /**
     * 是否同一年
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    boolean isSameYear(IDate iDate);

    /**
     * 同年同月
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    boolean isSameYearAndMonth(Date date);

    /**
     * 同年同月
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    boolean isSameYearAndMonth(IDate iDate);

    /**
     * 同月同日
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    boolean isSameMonthAndDay(Date date);

    /**
     * 同月同日
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    boolean isSameMonthAndDay(IDate iDate);

    /**
     * 是否是今年
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    boolean isCurrentYear();

    /**
     * 是否为今天
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    boolean isToday();

    /**
     * 是否为昨天
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    boolean isYesterday();

    /**
     * 是否为明天
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    boolean isTomorrow();

    /**
     * 检查是否为00:00:00
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    boolean isStartOfDay();

    /**
     * 检查是是否为23:59:59
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    boolean isEndOfDay();

}
