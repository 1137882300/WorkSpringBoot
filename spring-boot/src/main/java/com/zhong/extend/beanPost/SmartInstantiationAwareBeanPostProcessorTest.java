package com.zhong.extend.beanPost;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;

/**
 * @author: juzi
 * @date: 2023/6/29
 * @desc:
 */
public class SmartInstantiationAwareBeanPostProcessorTest {

    public static class test implements SmartInstantiationAwareBeanPostProcessor {

        //用于在实例化Bean对象之前进行自定义处理。可以在该方法中返回一个代理对象，用于替换原始的Bean对象实例。
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            return SmartInstantiationAwareBeanPostProcessor.super.postProcessBeforeInstantiation(beanClass, beanName);
        }

        //用于在实例化Bean对象之后进行自定义处理。可以在该方法中修改Bean对象的属性值。
        @Override
        public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
            return SmartInstantiationAwareBeanPostProcessor.super.postProcessAfterInstantiation(bean, beanName);
        }

        //用于在设置Bean对象的属性值之后进行自定义处理。可以在该方法中对属性值进行进一步的校验或处理。
        @Override
        public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
            return SmartInstantiationAwareBeanPostProcessor.super.postProcessProperties(pvs, bean, beanName);
        }

        //用于在调用Bean对象的初始化方法之前进行自定义处理。
        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            return SmartInstantiationAwareBeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
        }

        //用于在调用Bean对象的初始化方法之后进行自定义处理。
        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            return SmartInstantiationAwareBeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
        }
    }

}
