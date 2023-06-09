package com.zhong.repository.multiple.frist.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author: juzi
 * @date: 2023/6/9
 * @desc:
 */
@Configuration
@MapperScan(basePackages = TwoDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "twoSqlSessionFactory")
public class TwoDataSourceConfig {

    // two dao 扫描路径
    public static final String PACKAGE = "com.springboot.twoDao";
    // mybatis mapper扫描路径
    public static final String MAPPER_LOCATION = "classpath:mapper/twoMapper/*.xml";

    /**
     * 主数据源，Primary注解必须增加，它表示该数据源为默认数据源
     * 项目中还可能存在其他的数据源，如获取时不指定名称，则默认获取这个数据源，如果不添加，则启动时候回报错
     */
    @Primary
    @Bean(name = "twoDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.two")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 事务管理器，Primary注解作用同上
     */
    @Primary
    @Bean(name = "twoTransactionManager")
    public PlatformTransactionManager dataSourceTransactionManager(@Qualifier("twoDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * session工厂，Primary注解作用同上
     */
    @Primary
    @Bean(name = "twoSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("twoDataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(TwoDataSourceConfig.MAPPER_LOCATION));
        return sessionFactoryBean.getObject();

    }


}
