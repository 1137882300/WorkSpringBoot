package com.zhong.limiting;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author: juzi
 * @date: 2023/6/30
 * @desc: sentinel通常是需要结合springcloud-alibaba框架一起实用的，而且与框架集成之后，可以配合控制台一起使用达到更好的效果，
 * 实际上，sentinel官方也提供了相对原生的SDK可供使用，接下来就以这种方式进行整合。
 */
public class SentinelTest {

    @Target(value = ElementType.METHOD)
    @Retention(value = RetentionPolicy.RUNTIME)
    public @interface SentinelLimitAnnotation {

        String resourceName();

        int limitCount() default 5;
    }

    @Aspect
    @Component
    public static class SentinelMethodLimitAop {

        private static void initFlowRule(String resourceName, int limitCount) {
            List<FlowRule> rules = new ArrayList<>();
            FlowRule rule = new FlowRule();
            //设置受保护的资源
            rule.setResource(resourceName);
            //设置流控规则 QPS
            rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
            //设置受保护的资源阈值
            rule.setCount(limitCount);
            rules.add(rule);
            //加载配置好的规则
            FlowRuleManager.loadRules(rules);
        }

        @Pointcut(value = "@annotation(SentinelLimitAnnotation)")
        public void rateLimit() {

        }

        //该类的实现思路与上述使用guava类似，不同的是，这里使用的是sentinel原生的限流相关的API，
        @Around("rateLimit()")
        public Object around(ProceedingJoinPoint joinPoint) {
            //1、获取当前的调用方法
            Method currentMethod = getCurrentMethod(joinPoint);
            if (Objects.isNull(currentMethod)) {
                return null;
            }
            //2、从方法注解定义上获取限流的类型
            String resourceName = currentMethod.getAnnotation(SentinelLimitAnnotation.class).resourceName();
            if (StringUtils.isEmpty(resourceName)) {
                throw new RuntimeException("资源名称为空");
            }
            int limitCount = currentMethod.getAnnotation(SentinelLimitAnnotation.class).limitCount();
            initFlowRule(resourceName, limitCount);

            Entry entry = null;
            Object result = null;
            try {
                entry = SphU.entry(resourceName);
                try {
                    result = joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            } catch (BlockException ex) {
                // 资源访问阻止，被限流或被降级
                // 在此处进行相应的处理操作
                System.out.println("blocked");
                return "被限流了";
            } catch (Exception e) {
                Tracer.traceEntry(e, entry);
            } finally {
                if (entry != null) {
                    entry.exit();
                }
            }
            return result;
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
    }

    public static class Test {
        @GetMapping("/limit")
        @SentinelLimitAnnotation(limitCount = 1, resourceName = "sentinelLimit")
        public String sentinelLimit() {
            return "sentinelLimit";
        }
    }

}
