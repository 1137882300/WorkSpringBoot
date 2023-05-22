package com.zhong.define.reality;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: juzi
 * @date: 2023/5/22
 * @desc:
 */
@Configuration
public class MultiTenantDataSourceConfiguration implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    @Autowired
    private Environment env;

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // 获取多租户数据源配置信息
        Map<String, Map<String, Object>> dataSourceConfigMap = getMultiTenantDataSourceConfigMap();

        // 创建并注册多个数据源 Bean
        dataSourceConfigMap.forEach((key, dataSourceConfig) -> {
            ComboPooledDataSource dataSource = createDataSource(dataSourceConfig);
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(ComboPooledDataSource.class, () -> dataSource);
            AbstractBeanDefinition definition = builder.getBeanDefinition();
            definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
            registry.registerBeanDefinition(key, definition);
        });
    }

    /**
     * 获取多租户数据源的配置信息
     */
    private Map<String, Map<String, Object>> getMultiTenantDataSourceConfigMap() {
        Map<String, Map<String, Object>> dataSourceConfigMap = new HashMap<>();

        // 从配置文件中获取所有数据源配置信息
        String[] dataSourceKeys = env.getProperty("datasource.keys", "").split(",");
        for (String dataSourceKey : dataSourceKeys) {
            String dataSourcePrefix = "datasource." + dataSourceKey;
            Map<String, Object> dataSourceConfig = new HashMap<>();
            dataSourceConfig.put("driverClass", env.getProperty(dataSourcePrefix + ".driverClass"));
            dataSourceConfig.put("jdbcUrl", env.getProperty(dataSourcePrefix + ".jdbcUrl"));
            dataSourceConfig.put("user", env.getProperty(dataSourcePrefix + ".username"));
            dataSourceConfig.put("password", env.getProperty(dataSourcePrefix + ".password"));
            dataSourceConfigMap.put(dataSourceKey, dataSourceConfig);
        }

        return dataSourceConfigMap;
    }

    /**
     * 根据配置信息创建 ComboPooledDataSource 实例
     */
    private ComboPooledDataSource createDataSource(Map<String, Object> dataSourceConfig) {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(String.valueOf(dataSourceConfig.get("driverClass")));
        dataSource.setJdbcUrl(String.valueOf(dataSourceConfig.get("jdbcUrl")));
        dataSource.setUser(String.valueOf(dataSourceConfig.get("user")));
        dataSource.setPassword(String.valueOf(dataSourceConfig.get("password")));
        return dataSource;
    }


    @Data
    static class ComboPooledDataSource {

        private String driverClass;
        private String jdbcUrl;
        private String user;
        private String password;

    }
}