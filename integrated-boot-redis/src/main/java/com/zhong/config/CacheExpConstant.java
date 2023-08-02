package com.zhong.config;

/**
 * 缓存过期时间
 * 使用  @Cacheable(cacheManager = CacheExpConstant.REDIS_CACHE_MANAGER, key = "'abc:aa'",value =CacheExpConstant.REDIS_HALF_HOUR )
 */
public class CacheExpConstant {


    public static final String REDIS_ONE_MINUTE = "REDIS_ONE_MINUTE"; //缓存1分钟
    public static final String REDIS_TEN_MINUTE = "REDIS_TEN_MINUTE"; //缓存10分钟
    public static final String REDIS_HALF_HOUR = "REDIS_HALF_HOUR"; //缓存半个小时
    public static final String REDIS_ONE_HOUR = "REDIS_ONE_HOUR"; //缓存一个个小时
    public static final String REDIS_TWO_HOUR = "REDIS_TWO_HOUR"; //缓存二个小时
    public static final String REDIS_ONE_DAY = "REDIS_ONE_DAY"; //缓存一天


    public static final String REDIS_CACHE_MANAGER = "redisCacheManager";
}
