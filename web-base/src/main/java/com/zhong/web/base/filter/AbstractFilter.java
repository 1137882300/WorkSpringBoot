package com.zhong.web.base.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author: juzi
 * @date: 2023/5/19
 * @desc:
 */
@Slf4j
public abstract class AbstractFilter implements Filter, AuthFilter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("=======> AbstractFilter init");
        log.info("=======> AbstractFilter init");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

    }

    @Override
    public void destroy() {
        System.out.println("=======> AbstractFilter destroy");
        Filter.super.destroy();
    }
}
