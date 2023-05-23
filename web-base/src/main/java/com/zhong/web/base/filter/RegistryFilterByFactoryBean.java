package com.zhong.web.base.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author: juzi
 * @date: 2023/5/19
 * @desc:
 */
@Slf4j
public class RegistryFilterByFactoryBean implements FactoryBean<AuthorizeBuilder> {


    @Override
    public AuthorizeBuilder getObject() throws Exception {
        log.info("RegistryFilterByFactoryBean getObject");
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
