package com.zhong.support.impl;

import com.zhong.support.IRedisRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 */
public class RedisRepositoryImpl<K, V> implements IRedisRepository<K, V> {

    private final RedisTemplate<K, V> redisTemplate;

    public RedisRepositoryImpl(RedisTemplate<K, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 设置值
     *
     * @author ashui
     * @date 2021/12/2
     */
    @Override
    public void set(K key, V value) {
        redisTemplate.opsForValue().set(key, value);
    }


    /**
     * 设置值
     *
     * @author ashui
     * @date 2021/12/2
     */
    @Override
    public void set(K key, V value, long time, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, time, timeUnit);
    }

    /**
     * 计数器
     *
     * @author juzi
     * @date 2023/4/6
     */
    @Override
    @SuppressWarnings("unchecked")
    public Optional<V> incr(K key) {
        Long val = redisTemplate.opsForValue().increment(key);
        if (val == null) {
            return Optional.empty();
        }
        return Optional.of((V) val);
    }

    /**
     * 设置过期时间
     *
     * @author juzi
     * @date 2023/4/6
     */
    @Override
    public boolean expire(K key, long time, TimeUnit timeUnit) {
        return Boolean.TRUE.equals(redisTemplate.expire(key, time, timeUnit));
    }


    /**
     * 获取值
     *
     * @author ashui
     * @date 2021/12/2
     */
    @SuppressWarnings("unchecked")
    @Override
    public Optional<V> get(K key) {
        Object val = redisTemplate.opsForValue().get(key);
        if (val == null) {
            return Optional.empty();
        }
        return Optional.of((V) val);
    }

    /**
     * 判断缓存是否存在
     */
    @Override
    public boolean containsKey(K key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    /**
     * 移除某个key
     */
    @Override
    public boolean delete(K key) {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

    /**
     * 移除前缀key
     */
    @SuppressWarnings("unchecked")
    @Override
    public void deleteKeysForPrefix(K prefixKey) {
        K prefix = (K) (prefixKey + "*");
        Set<K> keys = redisTemplate.keys(prefix);
        if (!CollectionUtils.isEmpty(keys)) {
            redisTemplate.delete(keys);
        }
    }

}
