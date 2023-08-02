package com.zhong.support.support;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;

/**
 * 配置
 */
public class ConfigAdapter {

    private static GlobalConfig getGlobalConfig() {
        GlobalConfig config = GlobalConfigUtils.defaults();
        config.setSqlInjector(new SqlInjectorAdapter());
        return config;
    }

    public static void config(MybatisSqlSessionFactoryBean sqlSessionFactoryBean) {
        //添加插件
        sqlSessionFactoryBean.setPlugins(new PaginationInterceptor());
        //配置扩展
        sqlSessionFactoryBean.setGlobalConfig(getGlobalConfig());
    }
}
