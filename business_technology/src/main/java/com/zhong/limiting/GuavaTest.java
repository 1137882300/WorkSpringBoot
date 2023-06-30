package com.zhong.limiting;

import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author: juzi
 * @date: 2023/6/30
 * @desc: 基于guava限流实现，令牌桶算法
 * 令牌 ：获取到令牌的Request才会被处理，其他Requests要么排队要么被直接丢弃；
 * 桶 ：用来装令牌的地方，所有Request都从这个桶里面获取令牌
 */
public class GuavaTest {

    //限流注解
    @Target(value = ElementType.METHOD)
    @Retention(value = RetentionPolicy.RUNTIME)
    public @interface RateConfigAnno {
        String limitType();

        double limitCount() default 5d;
    }


    //限流AOP类
    //通过AOP前置通知的方式拦截添加了上述自定义限流注解的方法，解析注解中的属性值，并以该属性值作为guava提供的限流参数，该类为整个实现的核心所在。
    @Aspect
    @Component
    public static class GuavaLimitAop {

        private static final Logger logger = LoggerFactory.getLogger(GuavaLimitAop.class);

        @Before("execution(@RateConfigAnno * *(..))")
        public void limit(JoinPoint joinPoint) {
            //1、获取当前的调用方法
            Method currentMethod = getCurrentMethod(joinPoint);
            if (Objects.isNull(currentMethod)) {
                return;
            }
            //2、从方法注解定义上获取限流的类型
            String limitType = currentMethod.getAnnotation(RateConfigAnno.class).limitType();
            double limitCount = currentMethod.getAnnotation(RateConfigAnno.class).limitCount();
            //使用guava的令牌桶算法获取一个令牌，获取不到先等待
            RateLimiter rateLimiter = RateLimitHelper.getRateLimiter(limitType, limitCount);
            boolean b = rateLimiter.tryAcquire();
            if (b) {
                System.out.println("获取到令牌");
            } else {
                HttpServletResponse resp = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("success", false);
                jsonObject.put("msg", "限流中");
                try {
                    assert resp != null;
                    output(resp, jsonObject.toJSONString());
                } catch (Exception e) {
                    logger.error("error: ", e);
                }
            }
        }

        private Method getCurrentMethod(JoinPoint joinPoint) {
            Method[] methods = joinPoint.getTarget().getClass().getMethods();
            Method target = null;
            for (Method method : methods) {
                if (method.getName().equals(joinPoint.getSignature().getName())) {
                    target = method;
                    break;
                }
            }
            return target;
        }

        public void output(HttpServletResponse response, String msg) throws IOException {
            response.setContentType("application/json;charset=UTF-8");
            ServletOutputStream outputStream = null;
            try {
                outputStream = response.getOutputStream();
                outputStream.write(msg.getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                assert outputStream != null;
                outputStream.flush();
                outputStream.close();
            }
        }
    }

    public static class RateLimitHelper {

        private RateLimitHelper() {
        }

        private static final Map<String, RateLimiter> rateMap = new HashMap<>();

        public static RateLimiter getRateLimiter(String limitType, double limitCount) {
            RateLimiter rateLimiter = rateMap.get(limitType);
            if (rateLimiter == null) {
                rateLimiter = RateLimiter.create(limitCount);
                rateMap.put(limitType, rateLimiter);
            }
            return rateLimiter;
        }
    }

    //测试
    @RestController
    public static class Test {

        @GetMapping("/save")
        @RateConfigAnno(limitType = "saveOrder", limitCount = 1)
        public String save() {
            return "success";
        }

    }
}
