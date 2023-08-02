package com.zhong.date;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 时间转换
 *
 * @author hengyu
 * @date 2021/09/07
 */
public interface IDateConvert {

    /**
     * toDate
     *
     * @author hengyu
     * @date 2021/9/6
     **/
    Date toDate();

    /**
     * toLocalDateTime
     *
     * @author hengyu
     * @date 2021/9/6
     **/
    LocalDateTime toLocalDateTime();

    /**
     * 获取当前时间13位毫秒时间戳
     *
     * @author hengyu
     * @date 2021/9/7
     **/
    Long toTimeStamp();

    /**
     * 获取当前时间10位秒时间戳
     *
     * @author hengyu
     * @date 2021/9/7
     **/
    Long toTimeStamp10();


}
