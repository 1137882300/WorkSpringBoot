package com.zhong.web.base.config.security;

import com.zhong.web.base.filter.RegistryFilterByFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: juzi
 * @date: 2023/5/22
 * @desc:
 */
@Configuration
public class SecurityAutoConfiguration {

    @Bean
    public RegistryFilterByFactoryBean filterFactoryBean() {
        return new RegistryFilterByFactoryBean();
    }

}
