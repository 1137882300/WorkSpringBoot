package com.zhong.define.bean;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

/**
 * @author: juzi
 * @date: 2023/5/29
 * @desc:
 */
@Configuration
@RequiredArgsConstructor
//@EnableConfigurationProperties({
//        DingTalkProperties.class,
//})
//@ComponentScan("com.youxiake.wenlv.sdk.dingtalk")
public class DefaultListableBeanFactoryDemo implements ApplicationContextAware, InitializingBean {

    private DefaultListableBeanFactory defaultListableBeanFactory;

    private ApplicationContext applicationContext;


    @Override
    public void afterPropertiesSet() throws Exception {

        String beanName = "null";
        defaultListableBeanFactory.registerSingleton(beanName, new GroupRobotServiceImpl());
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
        this.defaultListableBeanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    static class GroupRobotServiceImpl {
        GroupRobotServiceImpl() {

        }
    }
}
