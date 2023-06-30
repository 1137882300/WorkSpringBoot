package com.zhong.limiting;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/**
 * @author: juzi
 * @date: 2023/6/30
 * @desc: redis是线程安全的，天然具有线程安全的特性，支持原子性操作，限流服务不仅需要承接超高QPS，还要保证限流逻辑的执行层面具备线程安全的特性，
 * 利用Redis这些特性做限流，既能保证线程安全，也能保证性能。
 * 这里梳理出一个整体的实现思路：
 * 1. 编写lua脚本，指定入参的限流规则，比如对特定的接口限流时，可以根据某个或几个参数进行判定，调用该接口的请求，在一定的时间窗口内监控请求次数；
 * 2. 既然是限流，最好能够通用，可将限流规则应用到任何接口上，那么最合适的方式就是通过自定义注解形式切入；
 * 3. 提供一个配置类，被spring的容器管理，redisTemplate中提供了DefaultRedisScript这个bean；
 * 4. 提供一个能动态解析接口参数的类，根据接口参数进行规则匹配后触发限流；
 */
public class RedisLuaTest {

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Inherited
    @Documented
    public @interface RedisLimitAnnotation {

        /**
         * key
         */
        String key() default "";

        /**
         * Key的前缀
         */
        String prefix() default "";

        /**
         * 一定时间内最多访问次数
         */
        int count();

        /**
         * 给定的时间范围 单位(秒)
         */
        int period();

//        /**
//         * 限流的类型(用户自定义key或者请求ip)
//         */
//        LimitType limitType() default LimitType.CUSTOMER;

    }

    @Component
    public static class RedisConfiguration {

        @Bean
        public DefaultRedisScript<Number> redisluaScript() {
            DefaultRedisScript<Number> redisScript = new DefaultRedisScript<>();
            redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("limit.lua")));
            redisScript.setResultType(Number.class);
            return redisScript;
        }

        @Bean("redisTemplate")
        public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
            RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
            redisTemplate.setConnectionFactory(redisConnectionFactory);
            Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
            //设置value的序列化方式为JSOn
            redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
            //设置key的序列化方式为String
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setHashKeySerializer(new StringRedisSerializer());
            redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
            redisTemplate.afterPropertiesSet();
            return redisTemplate;
        }
    }

    @Aspect
    @Configuration
    public static class LimitRestAspect {

        private static final Logger logger = LoggerFactory.getLogger(LimitRestAspect.class);

        @Resource
        private RedisTemplate<String, Object> redisTemplate;

        @Resource
        private DefaultRedisScript<Number> redisluaScript;


        @Pointcut(value = "@annotation(RedisLimitAnnotation)")
        public void rateLimit() {

        }

        @Around("rateLimit()")
        public Object interceptor(ProceedingJoinPoint joinPoint) throws Throwable {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            Class<?> targetClass = method.getDeclaringClass();
            RedisLimitAnnotation rateLimit = method.getAnnotation(RedisLimitAnnotation.class);
            if (rateLimit != null) {
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                String ipAddress = getIpAddr(request);
                String stringBuffer = ipAddress + "-" +
                        targetClass.getName() + "- " +
                        method.getName() + "-" +
                        rateLimit.key();
                List<String> keys = Collections.singletonList(stringBuffer);
                //调用lua脚本，获取返回结果，这里即为请求的次数
                Number number = redisTemplate.execute(
                        redisluaScript,
                        keys,
                        rateLimit.count(),
                        rateLimit.period()
                );
                if (number != null && number.intValue() != 0 && number.intValue() <= rateLimit.count()) {
                    logger.info("限流时间段内访问了第：{} 次", number);
                    return joinPoint.proceed();
                }
            } else {
                return joinPoint.proceed();
            }
            throw new RuntimeException("访问频率过快，被限流了");
        }

        /**
         * 获取请求的IP方法
         */
        private String getIpAddr(HttpServletRequest request) {
            String ipAddress;
            try {
                ipAddress = request.getHeader("x-forwarded-for");
                if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                    ipAddress = request.getHeader("Proxy-Client-IP");
                }
                if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                    ipAddress = request.getHeader("WL-Proxy-Client-IP");
                }
                if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                    ipAddress = request.getRemoteAddr();
                }
                // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
                if (ipAddress != null && ipAddress.length() > 15) {
                    if (ipAddress.indexOf(",") > 0) {
                        ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                    }
                }
            } catch (Exception e) {
                ipAddress = "";
            }
            return ipAddress;
        }
    }

    @RestController
    public static class Test {

        //localhost:8081/redis/limit
        @GetMapping("/redis/limit")
        @RedisLimitAnnotation(key = "queryFromRedis", period = 1, count = 1)
        public String queryFromRedis() {
            return "success";
        }

    }
}
