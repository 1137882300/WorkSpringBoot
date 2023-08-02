package com.zhong.date;

import java.util.Date;

/**
 * @author hengyu
 * 相差年，月，日，时，分，秒
 * @date 2021/09/07
 */
public interface IDateDifference {

    /**
     * 计算两个日期相差年数
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    int diffInYears(Date endDate);

    /**
     * 计算两个日期相差年数
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    int diffInYears(IDate endIDate);

    /**
     * 计算两个日期相差月数
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    int diffInMonths(Date endDate);

    /**
     * 计算两个日期相差月数
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    int diffInMonths(IDate endIDate);

    /**
     * 计算两个日期相差天数
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    int diffInDays(Date endDate);

    /**
     * 计算两个日期相差天数
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    int diffInDays(IDate endIDate);

    /**
     * 计算两个日期相差时
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    int diffInHours(Date endDate);

    /**
     * 计算两个日期相差时
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    int diffInHours(IDate endIDate);

    /**
     * 计算两个日期相差分
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    int diffInMinutes(Date endDate);

    /**
     * 计算两个日期相差分
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    int diffInMinutes(IDate endIDate);

    /**
     * 计算两个日期相差秒
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    int diffInSeconds(Date endDate);

    /**
     * 计算两个日期相差秒
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    int diffInSeconds(IDate endIDate);

}
