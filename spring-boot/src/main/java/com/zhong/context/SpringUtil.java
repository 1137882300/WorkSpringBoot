package com.zhong.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: juzi
 * @date: 2023/6/2
 * @desc:
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@Nullable ApplicationContext applicationContext) throws BeansException {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
    }


    /**
     * @desc 获取applicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    /**
     * @desc 通过name获取Bean
     */
    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }


    /**
     * @desc 通过class获取Bean
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * @desc 判断是否含有bean
     */
    public static boolean containsBean(String name) {
        return getApplicationContext().containsBean(name);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * @desc 返回bean，没有则返回null
     */
    public static <T> T getBeanOrNull(Class<T> clazz) {
        return BeanFactoryUtils.beanNamesForTypeIncludingAncestors(applicationContext, clazz).length > 0
                ? applicationContext.getBean(clazz) : null;
    }


    /**
     * @desc 获取所有类型的bean
     */
    public static <T> Map<String, T> getBeans(Class<T> clazz) {
        return applicationContext.getBeansOfType(clazz);
    }
}
