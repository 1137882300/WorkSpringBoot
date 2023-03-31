package com.zhong.redis.service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @date 2022/7/18 16:04
 */
public interface RedisService {

    int remove(String... keys);

    boolean remove(String key);

    boolean exists(String key);

    String get(String key);

    <T> T getObject(String key, Class<T> type);

    <T> List<T> getArray(String key, Class<T> clazz);

    String get(String key, String defaultValue);

    boolean set(String key, List<Object> value);

    boolean setNX(String key, Object value);

    boolean setNX(String key, Object value, long expireTime);

    boolean setNX(String key, Object value, long expireTime, TimeUnit timeUnit);

    boolean setNull(String key);

    boolean set(String key, Object value);

    boolean set(String key, Object value, long expireTime);

    boolean set(String key, Object value, long expireTime, TimeUnit timeUnit);

    Set<String> keys(String key);

    long getExpire(String key);

    boolean expire(String key, long time, TimeUnit timeUnit);

    boolean expireAt(String key, Date date);

    boolean multiSet(Map<String, String> maps);

    boolean multiSetObject(Map<String, Object> maps);

    Long incr(String key);

    Long incr(String key, Long incValue);

    <T> T leftPop(String key, Class<T> type);

    Long leftPush(String key, Object val);

    Long leftPushAll(String key, Object[] val);

    Long leftPushAll(String key, List<Object> list);

    Long leftPush(String key, Object val, int maxSize);

    List<Object> getListRange(String key, long min, long max);

    Long size(String key);

    Object hGet(String key, String field);

    Long hSize(String key);

    Map<Object, Object> hGetAll(String key);

    List<Object> hMultiGet(String key, Collection<Object> fields);

    boolean hPut(String key, String hashKey, Object value);

    boolean hPutAll(String key, Map<String, String> maps);

    Long hDelete(String key, Object... fields);

    boolean hExists(String key, String field);

    boolean addSet(String key, Object value);

    boolean removeSet(String key, Object... value);

    Set<Object> getAllSet(String key);

    Boolean addZSet(String key, Object value, double score);

    Long removeZSet(String key, Object... value);

    Double incrZSet(String key, Object value, double delta);

    Double incrZSet(String key, Object value);

    Set<Object> zSetReverseRangeByScore(String key, double min, double max);

    Set<Object> zSetRangeByScore(String key, double min, double max);

}
