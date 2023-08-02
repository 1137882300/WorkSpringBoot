package com.zhong.config;

import com.zhong.config.enums.CacheExpEnum;
import com.zhong.config.enums.SerializerEnum;
import com.zhong.config.fast.FastJsonRedisSerializer;
import com.zhong.support.RedisRepositoryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @desc redis 配置
 */
@Slf4j
@Configuration
public class RedisConfiguration extends CachingConfigurerSupport {

    /**
     * @desc 错误处理器
     */
    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        return new DefaultCacheErrorHandler();
    }


    /**
     * @desc 注解@Cache key生成规则
     */
    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

    /**
     * @desc RedisCacheManager
     * 使用 @Cacheable(cacheManager = CacheExpConstant.REDIS_CACHE_MANAGER, key = "'abc:aa'",value =CacheExpConstant.REDIS_HALF_HOUR )
     */
    @Bean("redisCacheManager")
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        Map<String, RedisCacheConfiguration> configMap = new HashMap<>(12);
        for (SerializerEnum value : SerializerEnum.values()) {
            RedisSerializer<?> serializer = value.getSerializer();
            RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                    .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                    .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer)).disableKeyPrefix();
            Map<String, RedisCacheConfiguration> map = Arrays.stream(CacheExpEnum.values())
                    .collect(Collectors.toMap(it -> getCacheName(it, value), it -> config.entryTtl(Duration.ofSeconds(it.seconds))));
            configMap.putAll(map);
        }
        return RedisCacheManager.builder(factory)
                .withInitialCacheConfigurations(configMap)
                .build();
    }

    /**
     * @desc 获取缓存名称
     * @author ashui
     * @date 2021/7/20
     */
    private String getCacheName(CacheExpEnum expEnum, SerializerEnum serializerEnum) {
        return expEnum.name;
    }


    @Bean("fastRedisTemplate")
    public StringRedisTemplate redisTemplateJson(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate();
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        template.setValueSerializer(fastJsonRedisSerializer);
        template.setHashValueSerializer(fastJsonRedisSerializer);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setConnectionFactory(factory);
        return template;
    }


    @Bean
    public RedisRepositoryFactory<?> redisRepositoryFactory(@Qualifier("fastRedisTemplate") StringRedisTemplate redisTemplate) {
        return new RedisRepositoryFactory<>(redisTemplate);
    }


}