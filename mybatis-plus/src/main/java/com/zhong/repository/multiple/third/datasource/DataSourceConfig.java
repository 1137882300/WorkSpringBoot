package com.zhong.repository.multiple.third.datasource;

import com.zhong.repository.multiple.third.enums.DataSourceType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: juzi
 * @date: 2023/6/9
 * @desc:
 */

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.remote")
    public DataSource remoteDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.local")
    public DataSource localDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource dataSource(DataSource remoteDataSource, DataSource localDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.REMOTE.name(), remoteDataSource);
        targetDataSources.put(DataSourceType.LOCAL.name(), localDataSource);
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 注入目标数据源，可以是多个
        dynamicDataSource.setTargetDataSources(targetDataSources);
        // 注入默认数据源，只能是一个
        dynamicDataSource.setDefaultTargetDataSource(localDataSource);
        return dynamicDataSource ;
    }

}
