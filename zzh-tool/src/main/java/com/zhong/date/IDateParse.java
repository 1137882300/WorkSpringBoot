package com.zhong.date;


import com.zhong.date.enums.DateFormatEnum;

import java.util.Date;

/**
 * 时间解析
 *
 * @author ashui
 * @date 2021/9/8
 */
public interface IDateParse {


    /**
     * 指定格式解析日期
     * @author ashui
     * @date 2022/5/11
     */
    Date parse(String datetime, DateFormatEnum dateFormatEnum);

    /**
     * yyyy-MM-dd hh:mm:ss字符串转时间
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    Date parseDateTimeString(String datetime);

    /**
     * yyyy-MM-dd字符串转时间
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    Date parseDateString(String date);

    /**
     * 设置年
     *
     * @author hengyu
     * @date 2021/9/6
     **/
    Date parse(int years, int month, int day, int hour, int minute, int second);

    /**
     * 设置年
     *
     * @author hengyu
     * @date 2021/9/6
     **/
    Date parse(int year, int month, int day);

    /**
     * 10,13位毫秒时间戳转Date
     *
     * @author hengyu
     * @date 2021/9/7
     **/
    Date parse(Long timestamp);


}
