package com.zhong.date.impl;


import com.zhong.date.AbstractDate;
import com.zhong.date.DateWrapper;
import com.zhong.date.IDateConvert;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author ashui
 * @date 2021/9/8
 */
public class DefaultDateConvert extends AbstractDate implements IDateConvert {

    public DefaultDateConvert(DateWrapper warpper) {
        super(warpper);
    }

    /**
     * toDate
     *
     * @author hengyu
     * @date 2021/9/6
     **/
    @Override
    public Date toDate() {
        return wrapper.getDate();
    }

    /**
     * toLocalDateTime
     *
     * @author hengyu
     * @date 2021/9/6
     **/
    @Override
    public LocalDateTime toLocalDateTime() {
        return wrapper.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * 获取当前时间13位毫秒时间戳
     *
     * @author hengyu
     * @date 2021/9/7
     **/
    @Override
    public Long toTimeStamp() {
        return wrapper.getDate().getTime();
    }

    /**
     * 获取当前时间10位秒时间戳
     *
     * @author hengyu
     * @date 2021/9/7
     **/
    @Override
    public Long toTimeStamp10() {
        return toTimeStamp() / 1000;
    }
}
