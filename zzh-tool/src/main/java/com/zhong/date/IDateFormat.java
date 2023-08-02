package com.zhong.date;


import com.zhong.date.enums.DateFormatEnum;
import com.zhong.date.enums.ZhDateFormatEnum;

/**
 * 时间格式化
 *
 * @author hengyu
 * @date 2021/09/07
 */
public interface IDateFormat {

    /**
     * 时间转字符串:yyyy-MM-dd HH:mm:ss
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    String toDateTimeString();

    /**
     * 时间转字符串 yyyy-MM-dd
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    String toDateString();

    /**
     * 时间转字符串:HH:mm:ss
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    String toTimeString();

    /**
     * 时间格式化
     *
     * @author hengyu
     * @date 2021/9/6
     **/
    String format(DateFormatEnum formatEnum);

    /**
     * 时间中文格式化
     *
     * @author hengyu
     * @date 2021/9/6
     **/
    String format(ZhDateFormatEnum formatZhEnum);

    /**
     * 时间转字符串
     *
     * @author hengyu
     * @date 2021/9/6
     **/
    String format(String format);


}
