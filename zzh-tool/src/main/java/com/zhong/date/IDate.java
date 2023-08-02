package com.zhong.date;

/**
 * @author hengyu
 * @date 2021/09/06
 */
public interface IDate extends IDateCompare, IDateConvert, IDateObtain, IDateOperation, IDateFormat, IDateDifference, IDateWeek {

    /**
     * 克隆当前对象
     *
     * @author hengyu
     * @date 2021/9/10
     **/
    IDate clone();

}
