package com.zhong.config.enums;

import com.zhong.config.fast.FastJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * 缓存序列方式
 *
 */
public enum SerializerEnum {
    FAST("FASTJOSN", "FAST 支持"),
    ;


    public final String name;
    public final String desc;

    SerializerEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public RedisSerializer<?> getSerializer() {
//        if (this.equals(SerializerEnum.JACKSON)) {
//            Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//            //解决查询缓存转换异常的问题
//            ObjectMapper om = new ObjectMapper();
//            om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//            om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//            jackson2JsonRedisSerializer.setObjectMapper(om);
//            return jackson2JsonRedisSerializer;
//        }
        return new FastJsonRedisSerializer<>(Object.class);
    }

}
