package com.zhong.extend.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: juzi
 * @date: 2023/6/29
 * @desc: 使用MethodInterceptor接口，你可以在目标方法执行前、执行后或异常时插入自定义的逻辑代码，实现对方法调用的拦截和增强。
 * 切面实现
 */
public class MethodInterceptorTest {


    public static class MyMethodInterceptor implements MethodInterceptor {

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            // 在方法执行前执行的逻辑
            System.out.println("Before method: " + invocation.getMethod().getName());

            // 执行原始方法
            Object result = invocation.proceed();

            // 在方法执行后执行的逻辑
            System.out.println("After method: " + invocation.getMethod().getName());

            return result;
        }
    }

    @Configuration
    public static class MethodInterceptorConfig {
        //注意该地址为项目具体包地址
        public static final String traceExecution = "execution(* com.zhong.demo.controller..*.*(..))";

        @Bean
        public DefaultPointcutAdvisor defaultPointcutAdvisor() {
            MyMethodInterceptor interceptor = new MyMethodInterceptor();
            AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
            pointcut.setExpression(traceExecution);

            // 配置增强类advisor
            DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
            advisor.setPointcut(pointcut);
            advisor.setAdvice(interceptor);
            return advisor;
        }
    }

}
