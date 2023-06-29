package com.zhong.extend;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * @author: juzi
 * @date: 2023/6/29
 * @desc: 提供了对静态方法切入点的匹配功能。
 * 通过继承StaticMethodMatcherPointcut类，可以自定义一个静态方法切入点判断的实现。
 * 在Spring AOP的拦截器链中，该切入点所匹配的静态方法将会被拦截和增强。
 */
public class StaticMethodMatcherPointcutTest {

    public static class MyStaticMethodMatcherPointcut extends StaticMethodMatcherPointcut {
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            // 在此处添加自定义的静态方法切入点匹配逻辑
            // 返回 true 表示匹配成功，将会应用该切点逻辑
            // 返回 false 表示匹配失败，将会跳过该切点逻辑
            return false;
        }

        @Override
        public ClassFilter getClassFilter() {
            return ClassFilter.TRUE; // 匹配所有类
        }

    }
}
