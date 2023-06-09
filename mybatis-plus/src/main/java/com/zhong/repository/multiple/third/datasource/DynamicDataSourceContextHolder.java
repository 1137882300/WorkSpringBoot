package com.zhong.repository.multiple.third.datasource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: juzi
 * @date: 2023/6/9
 * @desc:
 */
public class DynamicDataSourceContextHolder {

    /*
     * 使用ThreadLocal维护变量，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
     * 所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    /*
     * 管理所有的数据源id,用于数据源的判断
     */
    public static List<String> datasourceId = new ArrayList<>();

    /**
     * @Description: 设置数据源的变量
     */
    public static void setDateSourceType(String dateSourceType){
        CONTEXT_HOLDER.set(dateSourceType);
    }

    /**
     * @Description: 获得数据源的变量
     */
    public static String getDateSourceType(){
        return CONTEXT_HOLDER.get();
    }

    /**
     * @Description: 清空所有的数据源变量
     */
    public static void clearDateSourceType(){
        CONTEXT_HOLDER.remove();
    }

    /**
     * @Description: 判断数据源是否已存在
     */
    public static boolean existDateSource(String dateSourceType ){
        return datasourceId.contains(dateSourceType);
    }
}
