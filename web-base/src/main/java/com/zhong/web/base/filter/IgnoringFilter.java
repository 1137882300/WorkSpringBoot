package com.zhong.web.base.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author: juzi
 * @date: 2023/5/19
 * @desc:
 */
public class IgnoringFilter extends AbstractFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("=======> IgnoringFilter doFilter");
        chain.doFilter(request, response);
    }

    @Override
    public SecurityFilterEnum getAuthEnum() {
        return SecurityFilterEnum.ignoring;
    }
}
