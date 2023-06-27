package com.zhong.configure;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * @author: juzi
 * @date: 2023/6/27
 * @desc:
 */
@Data
@Configuration
@PropertySources({
        @PropertySource(value = "classpath:pei-zhi-wen-jian1.properties", encoding = "utf-8"),
        @PropertySource(value = "classpath:pei-zhi-wen-jian2.properties", encoding = "utf-8")
})
//也能这样表示
//@PropertySource(value = { "config/db-config.properties" })
public class PropertySourcesTest {

    @Value("${env101.var10}")
    private String var10;

    @Value("${env101.var9}")
    private String var9;

}
