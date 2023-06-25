package com.zhong.define.bean;

import com.zhong.base.entity.EntityTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: juzi
 * @date: 2023/5/22
 * @desc:
 */
@Configuration
public class ConfigDemo {

    @Bean(name = "entityTest")
    public EntityTest entityTest() {
        return new EntityTest();
    }

}
