package com.zhong.config.enums;

/**
 * 缓存过期时间
 *
 */
public enum CacheExpEnum {
    ONE_MINUTE("REDIS_ONE_MINUTE", "缓存1分钟", 60L),
    TEN_MINUTE("REDIS_TEN_MINUTE", "缓存10分钟", 600L),
    HALF_HOUR("REDIS_HALF_HOUR", "缓存半个小时", 1800L),
    ONE_HOUR("REDIS_ONE_HOUR", "缓存一个个小时", 3600L),
    TWO_HOUR("REDIS_TWO_HOUR", "缓存二个小时", 3600L * 2),
    ONE_DAY("REDIS_ONE_DAY", "缓存一天", 3600L * 24),
    ;




    public final String name;
    public final String desc;
    public final long seconds;

    CacheExpEnum(String name, String desc, long seconds) {
        this.name = name;
        this.desc = desc;
        this.seconds = seconds;
    }
}
