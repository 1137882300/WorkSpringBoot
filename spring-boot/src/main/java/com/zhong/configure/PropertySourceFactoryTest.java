package com.zhong.configure;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.util.Properties;

/**
 * @author: juzi
 * @date: 2023/6/27
 * @desc:
 */
public class PropertySourceFactoryTest {

    @Data
    @Configuration
    @PropertySources({
            @org.springframework.context.annotation.PropertySource(value = "classpath:yaml文件.yaml", encoding = "utf-8", factory = YamlPropertySourceFactory.class)
    })
    public class PropertySourcesConf2 {

        @Value("${env101.var10}")
        private String var10;

        @Value("${env101.var9}")
        private String var9;
    }

    //适配器
    public class YamlPropertySourceFactory implements PropertySourceFactory {
        @Override
        public PropertySource<?> createPropertySource(String name, EncodedResource encodedResource) {
            YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
            factory.setResources(encodedResource.getResource());

            Properties properties = factory.getObject();

            return new PropertiesPropertySource(encodedResource.getResource().getFilename(), properties);
        }
    }
}
