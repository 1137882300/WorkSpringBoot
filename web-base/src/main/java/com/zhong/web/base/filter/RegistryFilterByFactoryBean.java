package com.zhong.web.base.filter;

import org.springframework.beans.factory.FactoryBean;

import javax.servlet.Filter;

/**
 * @author: juzi
 * @date: 2023/5/19
 * @desc:
 */
public class RegistryFilterByFactoryBean implements FactoryBean<AuthorizeBuilder> {


    @Override
    public AuthorizeBuilder getObject() throws Exception {
        return AuthorizeBuilder.builder()
                .register(new DenyFilter())
                .register(new IgnoringFilter())
                .build();
    }

    @Override
    public Class<?> getObjectType() {
        return AuthorizeBuilder.class;
    }
}
