package com.zhong.limiting;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

/**
 * @author: juzi
 * @date: 2023/6/30
 * @desc: 基于java自带的Semaphore限流
 */
public class SemaphoreTest {

    @Documented
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ShLimiter {
        int value() default 50;
    }

    @Aspect
    @Component
    @Slf4j
    public static class SemaphoreLimiterAop {

        private final Map<String, Semaphore> semaphores = new ConcurrentHashMap<>();
        private final static Logger LOG = LoggerFactory.getLogger(SemaphoreLimiterAop.class);

        @Pointcut("@annotation(ShLimiter)")
        public void aspect() {

        }

        @Around(value = "aspect()")
        public Object around(ProceedingJoinPoint point) throws Throwable {
            log.debug("进入限流aop");
            Object target = point.getTarget();
            String targetName = target.getClass().getName();
            String methodName = point.getSignature().getName();
            Object[] arguments = point.getArgs();
            Class<?> targetClass = Class.forName(targetName);
            Class<?>[] argTypes = ReflectUtils.getClasses(arguments);
            Method method = targetClass.getDeclaredMethod(methodName, argTypes);
            // 获取目标method上的限流注解@Limiter
            ShLimiter limiter = method.getAnnotation(ShLimiter.class);
            Object result;
            if (null != limiter) {
                // 以 class + method + parameters为key，避免重载、重写带来的混乱
                String key = targetName + "." + methodName + Arrays.toString(argTypes);
                // 获取限定的流量
                Semaphore semaphore = semaphores.get(key);
                if (null == semaphore) {
                    semaphores.putIfAbsent(key, new Semaphore(limiter.value()));
                    semaphore = semaphores.get(key);
                }
                try {
                    semaphore.acquire();
                    result = point.proceed();
                } finally {
                    if (null != semaphore) {
                        semaphore.release();
                    }
                }
            } else {
                result = point.proceed();
            }
            log.debug("退出限流");
            return result;
        }

    }
}
