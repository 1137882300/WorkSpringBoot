package com.zhong.web.base.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;

/**
 * @author: juzi
 * @date: 2023/5/19
 * @desc: 注册 过滤器 Filter
 * 这种方式，只能一个一个注册bean
 */
//@Configuration
public class RegistryFilterConfig {


    /**
     * @author juzi
     * @date 2023/5/19 下午 5:03
     * @description 注册 过滤器 Filter
     */
    @Order(1)
    @Bean
    public FilterRegistrationBean<Filter> oneFilter() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new IgnoringFilter());

        return bean;
    }

    @Order(2)
    @Bean
    public FilterRegistrationBean<Filter> twoFilter() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new IgnoringFilter());

        return bean;
    }
}
