package com.zhong.support;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @desc 数据源配置接口
 */
public interface DataSourceConfigurer {

    DataSource dataSource();

    SqlSessionFactory sqlSessionFactory(DataSource  dataSource) throws Exception;

    DataSourceTransactionManager dataSourceTransactionManager(DataSource  dataSource);

    MapperScannerConfigurer mapperScannerConfigurer();

}
