package com.zhong.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;

/**
 * 错误处理器
 * @author ashui
 * @date 2021/5/8
 */
@Slf4j
public class DefaultCacheErrorHandler implements CacheErrorHandler {

    @Override
    public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
        log.error("redis异常：key=" + key, e);
    }

    @Override
    public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
        log.error("redis异常：key=" + key, e);
    }

    @Override
    public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
        log.error("redis异常：key=" + key, e);
    }

    @Override
    public void handleCacheClearError(RuntimeException e, Cache cache) {
        log.error("redis异常", e);
    }
}
