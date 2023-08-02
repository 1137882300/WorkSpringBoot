package com.zhong.repository.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zhong.support.DataSourceConfigurer;
import com.zhong.support.support.ConfigAdapter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @desc 数据源配置
 */
@Configuration
public class MasterDataSourceConfigurer implements DataSourceConfigurer {

    public static final String DB_MASTER = "master";
    public static final String DB_TRANSACTION = DB_MASTER + "DataSourceTransactionManager";

    @Override
    @Bean(name = DB_MASTER + "DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.schemas")
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    @Override
    @Bean(DB_MASTER + "SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier(DB_MASTER + "DataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        //mapper 的xml 位置
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/" + DB_MASTER + "/*.xml"));

        ConfigAdapter.config(sqlSessionFactoryBean);
        return sqlSessionFactoryBean.getObject();
    }

    @Override
    @Bean(DB_TRANSACTION)
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier(DB_MASTER + "DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Override
    @Bean(DB_MASTER + "MapperScannerConfigurer")
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName(DB_MASTER + "SqlSessionFactory");
        //mapper接口位置
        mapperScannerConfigurer.setBasePackage("com.zhong.**.dao." + DB_MASTER + ".mapper");
        return mapperScannerConfigurer;
    }
}


