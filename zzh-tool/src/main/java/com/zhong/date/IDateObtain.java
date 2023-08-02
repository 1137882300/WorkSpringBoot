package com.zhong.date;

/**
 * 时间点获取
 *
 * @author hengyu
 * @date 2021/09/07
 */
public interface IDateObtain {

    /**
     * 当前天0点
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    IDate startOfDay();

    /**
     * 当前天23点59分59
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    IDate endOfDay();

    /**
     * 获取当前月第一天
     *
     * @author hengyu
     * @date 2021/9/7
     **/
    IDate firstDayOfMonth();

    /**
     * 获取当前月最后一天
     *
     * @author hengyu
     * @date 2021/9/7
     **/
    IDate lastDayOfMonth();

    /**
     * 获取当前年第一天
     *
     * @author hengyu
     * @date 2021/9/7
     **/
    IDate firstDayOfYear();

    /**
     * 获取当前年最后一天
     *
     * @author hengyu
     * @date 2021/9/7
     **/
    IDate lastDayOfYear();

    /**
     * 获取当前周第一天
     *
     * @author hengyu
     * @date 2021/9/7
     **/
    IDate firstDayOfWeek();

    /**
     * 获取当前周最后一天
     *
     * @author hengyu
     * @date 2021/9/7
     **/
    IDate lastDayOfWeek();

    /**
     * 返回年份
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    Integer year();

    /**
     * 返回月
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    Integer month();

    /**
     * 返回日
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    Integer day();

    /**
     * 返回小时
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    Integer hour();

    /**
     * 返回分钟
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    Integer minute();

    /**
     * 返回秒
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    Integer second();

}
