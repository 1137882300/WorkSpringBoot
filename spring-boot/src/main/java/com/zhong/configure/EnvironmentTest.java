package com.zhong.configure;

import org.springframework.core.env.Environment;

import javax.annotation.Resource;

/**
 * @author: juzi
 * @date: 2023/6/27
 * @desc: 两种方式 getProperty，resolvePlaceholders，入参不一样
 */
public class EnvironmentTest {

    @Resource
    private Environment env;

    public void test() {
        env.getProperty("env101.var1");
    }

    public void test2() {
        env.resolvePlaceholders("${spring.cloud.nacos.server-addr:localhost:8848}");
    }

    public void test3() {
        env.getRequiredProperty("server.port");
    }

}
