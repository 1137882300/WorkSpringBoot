package com.zhong.define.bean;

import com.zhong.entity.EntityTest;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;
import java.util.Map;

/**
 * @author: juzi
 * @date: 2023/5/22
 * @desc: BeanDefinitionRegistryPostProcessor 的子类： ImportBeanDefinitionRegistrar
 */
public class BeanDefinitionRegistryPostProcessorDemo implements BeanDefinitionRegistryPostProcessor {


    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        // 创建一个bean的定义类的对象
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(EntityTest.class);
        // 将Bean 的定义注册到Spring环境
        registry.registerBeanDefinition("entityTest", rootBeanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // bean的名字为key, bean的实例为value
        Map<String, Object> beanMap = beanFactory.getBeansWithAnnotation(EnableService.class);
        BeanDefinition entityTest = beanFactory.getBeanDefinition("entityTest");
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Target(ElementType.TYPE)
    @Import(BeanDefinitionRegistryPostProcessorDemo.class)
    @interface EnableService {
        String name();
    }

}
