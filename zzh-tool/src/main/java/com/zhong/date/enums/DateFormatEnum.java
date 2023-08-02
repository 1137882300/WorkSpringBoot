package com.zhong.date.enums;

/**
 * @author hengyu
 * @date 2021/09/06
 */
public enum DateFormatEnum {

    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),
    YYYY_MM_DD_HH_MM("yyyy-MM-dd HH:mm"),
    YYYY_MM_DD_HH("yyyy-MM-dd HH"),
    YYYY_MM_DD("yyyy-MM-dd"),
    YYYY_MM("yyyy-MM"),
    YYYY_M("yyyy-M"),
    YYYY年MM月("yyyy年MM月"),
    HH_MM_SS("HH:mm:ss"),
    HH_MM("HH:mm"),
    MM_SS("mm:ss"),
    YYYYMMDDHHMMSS("yyyyMMddHHmmss"),
    YYYYMMDD("yyyyMMdd"),
    YYYYMMDD_WITH_DOT("yyyy.MM.dd"),
    YYYYMM("yyyyMM"),
    MMDDHHMMSS("MMddHHmmss"),
    HHMMSS("HHmmss"),
    MM_DD("MM-dd"),
    ;


    public String value;

    DateFormatEnum(String value) {
        this.value = value;
    }
}
