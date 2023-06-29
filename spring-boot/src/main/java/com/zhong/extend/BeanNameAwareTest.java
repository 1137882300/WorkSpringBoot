package com.zhong.extend;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

/**
 * @author: juzi
 * @date: 2023/6/29
 * @desc: 用于需要获取bean自身在容器中的id/name时。
 */
public class BeanNameAwareTest {

    @Component
    public static class MyBean implements BeanNameAware {

        private String beanName;

        @Override
        public void setBeanName(String name) {
            this.beanName = name;
        }

        public void printBeanName() {
            System.out.println("Bean name is: " + beanName);
        }
    }
}
