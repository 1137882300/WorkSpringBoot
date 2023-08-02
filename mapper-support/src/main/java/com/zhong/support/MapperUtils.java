package com.zhong.support;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.zhong.spring.SpringUtil;
import lombok.SneakyThrows;

/**
 * Mapper工具类
 */
public class MapperUtils {


    /**
     * 根据model 获取 IMapperAdapter
     */
    @SneakyThrows
    public static IMapperAdapter<?> getMapperAdapter(String modelStr) {
        final Class<?> modelClazz = Class.forName(modelStr);
        return getMapperAdapter(modelClazz);
    }

    /**
     * 根据model class 获取 IMapperAdapter
     */
    @SneakyThrows
    public static IMapperAdapter<?> getMapperAdapter(Class<?> modelClazz) {
        final String currentNamespace = SqlHelper.table(modelClazz).getCurrentNamespace();
        final Class<?> aClass = Class.forName(currentNamespace);
        return (IMapperAdapter<?>) SpringUtil.getBean(aClass);
    }
}
