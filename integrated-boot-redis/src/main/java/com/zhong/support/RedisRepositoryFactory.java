package com.zhong.support;

import com.zhong.support.impl.RedisRepositoryImpl;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存管理工厂
 */
public class RedisRepositoryFactory<V> {

    private final Map<Class<V>, IRedisRepository<String, V>> cache = new ConcurrentHashMap<>(16);
    private final RedisTemplate<String, V> redisTemplate;

    public RedisRepositoryFactory(RedisTemplate<String, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public IRedisRepository<String, V> get(Class<V> clazz) {
        IRedisRepository<String, V> redisRepository = cache.get(clazz);
        if (redisRepository != null) {
            return redisRepository;
        } else {
            redisRepository = create(clazz);
        }
        return redisRepository;
    }

    private synchronized IRedisRepository<String, V> create(Class<V> clazz) {
        IRedisRepository<String, V> redisRepository = cache.get(clazz);
        if (redisRepository == null) {
            redisRepository = new RedisRepositoryImpl<>(redisTemplate);
            cache.put(clazz, redisRepository);
        }
        return redisRepository;
    }

    static class Test {
        @Resource
        private RedisRepositoryFactory redisRepositoryFactory;

        @SuppressWarnings("unchecked")
        public void test() {
            IRedisRepository<String, String> iRedisRepository = redisRepositoryFactory.get(String.class);

            IRedisRepository<String, Integer> iRedisRepository1 = redisRepositoryFactory.get(Integer.class);
        }

    }

}
