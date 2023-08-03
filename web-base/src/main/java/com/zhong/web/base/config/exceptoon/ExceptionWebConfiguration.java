package com.zhong.web.base.config.exceptoon;

import com.zhong.web.base.exception.ExceptionHandlerManager;
import com.zhong.web.base.exception.GlobalExceptionResolver;
import com.zhong.web.base.exception.NotFoundViewResolver;
import com.zhong.web.base.exception.base.IExceptionLog;
import com.zhong.web.base.exception.view.IViewAndModeResolver;
import com.zhong.web.base.exception.view.IViewFactory;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;


/**
 * @desc web配置
 */
@Configuration
public class ExceptionWebConfiguration implements WebMvcConfigurer {


    @Resource
    private IViewFactory factory;
    @Resource
    private IExceptionLog exceptionLog;

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        GlobalExceptionResolver globalExceptionResolver = new GlobalExceptionResolver();
        globalExceptionResolver.setManager(handlerManager());
        globalExceptionResolver.setViewAndModeResolver(viewAndModeResolver());
        resolvers.add(0, globalExceptionResolver);
    }


    @Bean
    public ErrorViewResolver errorViewResolver() {
        return new NotFoundViewResolver(viewAndModeResolver());
    }

    @Bean
    public IViewAndModeResolver viewAndModeResolver() {
        return new IViewAndModeResolver.DefaultViewAndModeResolver(factory);
    }


    @Bean
    public ExceptionHandlerManager handlerManager() {
        return new ExceptionHandlerManager(exceptionLog);
    }


}
