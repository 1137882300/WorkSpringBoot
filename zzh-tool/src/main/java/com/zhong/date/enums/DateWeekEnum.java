package com.zhong.date.enums;

public enum DateWeekEnum {

    WEEK("星期X"),
    CHOU("周X"),
    EN("英文");
    public String value;

    DateWeekEnum(String value) {
        this.value = value;
    }
}
