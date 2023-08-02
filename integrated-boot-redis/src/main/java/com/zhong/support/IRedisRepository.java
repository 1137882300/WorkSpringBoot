package com.zhong.support;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * redis 操作接口
 */
public interface IRedisRepository<K, V> {


    /**
     * 设置值
     *
     * @author ashui
     * @date 2021/12/2
     */
    void set(K key, V value);


    /**
     * 设置值
     *
     * @author ashui
     * @date 2021/12/2
     */
    void set(K key, V value, long time, TimeUnit timeUnit);


    /**
     * 计数器
     *
     * @author juzi
     * @date 2023/4/6
     */
    Optional<V> incr(K key);

    /**
     * 设置过期时间
     *
     * @author juzi
     * @date 2023/4/6
     */
    boolean expire(K key, long time, TimeUnit timeUnit);

    /**
     * 获取值
     *
     * @author ashui
     * @date 2021/12/2
     */
    Optional<V> get(K key);


    /**
     * 判断缓存是否存在
     *
     * @author ashui
     * @date 2021/12/3
     */
    boolean containsKey(K key);

    /**
    * @author: 竹阳
    * @date: 2022/9/18
    * 移除某个key
    */
    boolean delete(K key);

    /**
     * @author: 竹阳
     * @date: 2022/9/18
     * 移除前缀key
     */
    void deleteKeysForPrefix(K prefixKey);
}
