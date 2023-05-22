package com.zhong.web.base.filter;

/**
 * @author: juzi
 * @date: 2023/5/19
 * @desc:
 */
public enum SecurityFilterEnum {

    auth("需要认证"),
    deny("禁止"),
    ignoring("忽略"),
    perms("允许,可以获取当前上下文信息"),
    ;

    SecurityFilterEnum(String name) {
        this.name = name;
    }

    private final String name;

    public static String getContextName() {
        return SecurityFilterEnum.class.getName();
    }

    public String getName() {
        return name;
    }
}
