package com.zhong.date;

/**
 * 时间操作
 *
 * @author hengyu
 * @date 2021/09/07
 */
public interface IDateOperation {

    /**
     * 日期增减年
     *
     * @author hengyu
     * @date 2021/8/19
     **/
    IDate addYears(int num);

    /**
     * 日期增减月份
     *
     * @author hengyu
     * @date 2021/8/19
     **/
    IDate addMonths(int num);

    /**
     * 日期增减周
     *
     * @author hengyu
     * @date 2021/8/19
     **/
    IDate addWeeks(int num);

    /**
     * 日期增减周
     *
     * @author hengyu
     * @date 2021/8/19
     **/
    IDate addDays(int num);

    /**
     * 日期增减小时
     *
     * @author hengyu
     * @date 2021/8/19
     **/
    IDate addHours(int num);

    /**
     * 日期增减分
     *
     * @author hengyu
     * @date 2021/8/19
     **/
    IDate addMinutes(int num);

    /**
     * 日期增减秒
     *
     * @author hengyu
     * @date 2021/8/19
     **/
    IDate addSeconds(int num);

    /**
     * 日期增减毫秒
     *
     * @author hengyu
     * @date 2021/8/19
     **/
    IDate addMilliseconds(int num);


}
