package com.zhong.extend;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author: juzi
 * @date: 2023/9/14
 * @desc: 实现Condition接口可以用于定义条件判断逻辑，并通过注解将其应用于Spring框架的组件装配过程。
 * @Conditional(MyCondition.class)
 */
public class ConditionTest {

    //根据自己的配置的逻辑判断是否开启功能
    public static class MyCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            String enabled = context.getEnvironment().getProperty("yxk.limit.enabled");
            return BooleanUtils.toBoolean(enabled);
            // 自定义条件判断逻辑
            // 返回 true 或 false
        }
    }


    /**
     * @author juzi
     * @date 2023/9/14 下午 2:36
     * @description 使用方式
     * 只有当MyCondition的matches()方法返回true时，myBean才会被创建。
     */
    @Configuration
    public static class MyConfiguration {

        @Bean
        @Conditional(MyCondition.class)
        public Object myBean() {
            // 根据条件实例化或配置Bean
            return new Object();
        }
    }

}
