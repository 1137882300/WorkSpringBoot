package com.zhong.date.enums;

/**
 * @author hengyu
 * @date 2021/09/06
 */
public enum ZhDateFormatEnum {

    YYYY_MM_DD_HH_MM_SS("yyyy年MM月dd日HH时mm分ss秒"),
    YYYY_MM_DD_HH_MM("yyyy年MM月dd日HH时mm分"),
    YYYY_MM_DD("yyyy年MM月dd日"),
    YYYY_MM_DD_HH("yyyy年MM月dd日HH时"),
    YYYY_MM("yyyy年MM月"),
    MM_DD("MM月dd日"),
    HH_MM_SS("HH时mm分ss秒"),
    HH_MM("HH时mm分"),
    MM_SS("mm分ss秒"),;

    public String value;

    ZhDateFormatEnum(String value) {
        this.value = value;
    }
}
