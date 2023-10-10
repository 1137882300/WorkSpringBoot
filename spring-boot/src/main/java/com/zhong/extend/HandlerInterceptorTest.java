package com.zhong.extend;

import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: juzi
 * @date: 2023/6/29
 * @desc: ☛☛☛implements HandlerInterceptor:HTTP请求的拦截器，用于实现在处理程序执行之前、之后或请求完成之后拦截和处理HTTP请求
 * tomcat 执行顺序：filter > servlet > interceptor > controller
 */
public class HandlerInterceptorTest {

    /**
     * @author juzi
     * @date 2023/6/29 上午 9:57
     * @description 两种方式：1.继承WebMvcConfigurationSupport   2.实现WebMvcConfigurer（推荐）
     */
    public static class MvcConfig extends WebMvcConfigurationSupport /*implements WebMvcConfigurer*/ {
        @Override
        protected void addInterceptors(@NonNull InterceptorRegistry registry) {
            registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/aliPay/**");
            super.addInterceptors(registry);
        }
    }

    public static class HttpInterceptor implements HandlerInterceptor {
        //// 在处理程序执行之前执行的逻辑
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//            可以提前设置一些东西

            //// 返回true表示继续执行请求处理程序，返回false表示中断请求处理
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }

        //// 在处理程序执行之后、且在视图渲染之前执行的逻辑
        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        }

        //// 在视图渲染完成之后执行的逻辑，可用于清理资源等操作
        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        }
    }
}
