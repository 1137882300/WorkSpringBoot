package com.zhong.date.impl;


import com.zhong.date.AbstractDate;
import com.zhong.date.DateWrapper;
import com.zhong.date.IDateFormat;
import com.zhong.date.enums.DateFormatEnum;
import com.zhong.date.enums.ZhDateFormatEnum;
import com.zhong.date.exception.WLDateException;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;

/**
 * @author ashui
 * @date 2021/9/8
 */
public class DefaultDateFormat extends AbstractDate implements IDateFormat {

    public DefaultDateFormat(DateWrapper warpper) {
        super(warpper);
    }

    /**
     * 时间转字符串:yyyy-MM-dd HH:mm:ss
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    @Override
    public String toDateTimeString() {
        return format(DateFormatEnum.YYYY_MM_DD_HH_MM_SS.value);
    }

    /**
     * 时间转字符串:yyyy-MM-dd
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    @Override
    public String toDateString() {
        return format(DateFormatEnum.YYYY_MM_DD.value);
    }

    /**
     * 时间转字符串:HH:mm:ss
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    @Override
    public String toTimeString() {
        return format(DateFormatEnum.HH_MM_SS.value);
    }

    /**
     * 设置日期
     *
     * @author hengyu
     * @date 2021/9/6
     **/
    @Override
    public String format(DateFormatEnum formatEnum) {
        return format(formatEnum.value);
    }

    /**
     * 时间中文格式化
     *
     * @param formatZhEnum
     * @author hengyu
     * @date 2021/9/6
     */
    @Override
    public String format(ZhDateFormatEnum formatZhEnum) {
        return format(formatZhEnum.value);
    }

    /**
     * 时间转字符串
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    @Override
    public String format(String format) {
        if (StringUtils.isBlank(format)) {
            WLDateException.throwException("格式不能为空");
        }
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(wrapper.getDate());
    }

}
