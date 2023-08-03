package com.zhong.web.base.config.exceptoon;

import com.zhong.web.base.exception.base.IExceptionLog;
import com.zhong.web.base.exception.view.DefaultJackson2ViewFactory;
import com.zhong.web.base.exception.view.IModelAdvice;
import com.zhong.web.base.exception.view.IViewFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 扩展加载bean
 */
@Configuration
public class SupportConfiguration {

    /**
     * @author juzi
     * @date 2023/8/3 下午 2:46
     * @description 同时使用@ConditionalOnMissingBean和@Bean注解时，它的作用是在某个条件下，如果容器中不存在相同类型的Bean，
     * 就创建并注册一个新的Bean到Spring的应用程序上下文中。
     */
    @Bean
    @ConditionalOnMissingBean(IViewFactory.class)
    public IViewFactory jackson2ViewFactory(ObjectProvider<IModelAdvice> modelAdvice) {
        return new DefaultJackson2ViewFactory(modelAdvice);
    }

    @Bean
    @ConditionalOnMissingBean(IModelAdvice.class)
    public IModelAdvice modelAdvice() {
        return new IModelAdvice.DefaultModelAdvice();
    }

    @Bean
    @ConditionalOnMissingBean(IExceptionLog.class)
    public IExceptionLog exception() {
        return new IExceptionLog.DefaultExceptionLog();
    }


}


