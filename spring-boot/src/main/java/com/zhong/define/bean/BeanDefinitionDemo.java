package com.zhong.define.bean;

import com.zhong.base.entity.EntityTest;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: juzi
 * @date: 2023/5/22
 * @desc: BeanDefinition
 * 声明式方式 配置Bean时，其底层都是使用的该方式进行配置
 */
public class BeanDefinitionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 创建一个beanDefinition，并设置对应的beanDefinition对象的bean的类型
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        beanDefinition.setBeanClass(EntityTest.class);

        // 将beanDefinition注册到Spring容器中，并设置对应的Bean的名字
        context.registerBeanDefinition("entityTest", beanDefinition);
        context.refresh();

        System.out.println(context.getBean("entityTest"));
    }

}
