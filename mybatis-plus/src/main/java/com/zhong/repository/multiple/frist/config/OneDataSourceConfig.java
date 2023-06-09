package com.zhong.repository.multiple.frist.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author: juzi
 * @date: 2023/6/9
 * @desc:
 */
@Configuration
@MapperScan(basePackages = OneDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "oneSqlSessionFactory")
public class OneDataSourceConfig {

    // one dao扫描路径
    public static final String PACKAGE = "com.springboot.oneDao";
    // mybatis mapper扫描路径
    public static final String MAPPER_LOCATION = "classpath:mapper/oneMapper/*.xml";

    @Bean(name = "oneDatasource")
    @ConfigurationProperties("spring.datasource.druid.one")
    public DataSource oracleDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "oneTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager() {
        return new DataSourceTransactionManager(oracleDataSource());
    }

    @Bean(name = "oneSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("oneDatasource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        //如果不使用xml的方式配置mapper，则可以省去下面这行mapper location的配置。
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(OneDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

}
