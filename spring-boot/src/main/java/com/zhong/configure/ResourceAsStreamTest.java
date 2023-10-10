package com.zhong.configure;


import lombok.SneakyThrows;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author: juzi
 * @date: 2023/10/10
 * @desc: getResourceAsStream
 */
public class ResourceAsStreamTest {


    @SneakyThrows
    public void test() {
        InputStream in = ResourceAsStreamTest.class.getClassLoader().getResourceAsStream("application.properties");
        Properties properties = new Properties();
        properties.load(in);
        in.close();


        //配置的值
        String property = properties.getProperty("spring.datasource.url");
    }

}
