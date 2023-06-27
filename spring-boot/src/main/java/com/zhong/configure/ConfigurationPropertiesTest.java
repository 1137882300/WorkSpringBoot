package com.zhong.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: juzi
 * @date: 2023/6/27
 * @desc:
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "env101")
public class ConfigurationPropertiesTest {

    private String var1;

    private String var2;
}


