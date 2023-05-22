package com.zhong.define.bean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author: juzi
 * @date: 2023/5/22
 * @desc:
 */
@Component
public class FactoryBeanDemo implements FactoryBean<Object> {

    @Override
    public Object getObject() throws Exception {
        //bean
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return Object.class;
    }
}
