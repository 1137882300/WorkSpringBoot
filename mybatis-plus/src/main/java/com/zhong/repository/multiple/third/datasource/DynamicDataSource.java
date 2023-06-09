package com.zhong.repository.multiple.third.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author: juzi
 * @date: 2023/6/9
 * @desc:
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 获得数据源
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDateSourceType();
    }

}