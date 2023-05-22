package com.zhong.define.bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

/**
 * @author: juzi
 * @date: 2023/5/22
 * @desc: 通过 @ImportResource 注解来引入一个 XML 配置文件中定义的 Bean，Spring会将其中定义的 Bean 纳入到容器管理中。
 */
@Configuration
@ImportResource("classpath:spring-config.xml")
public class ImportResourceDemo {


}
