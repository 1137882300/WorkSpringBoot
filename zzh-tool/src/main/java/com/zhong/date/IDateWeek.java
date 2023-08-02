package com.zhong.date;


import com.zhong.date.enums.DateWeekEnum;

/**
 * @author hengyu
 * @date 2021/09/09
 * 时间周
 */
public interface IDateWeek {

    /**
     * 返回星期几
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    String weekDay();

    /**
     * 根据枚举返回星期格式
     * see {@link DateWeekEnum}
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    String weekDay(DateWeekEnum dateWeekEnum);

    /**
    * @author: 竹阳
    * @date: 2022/11/17
    * 返回星期几数字
    */
    Integer weekDayNum();
}
