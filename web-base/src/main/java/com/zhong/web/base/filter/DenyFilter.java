package com.zhong.web.base.filter;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class DenyFilter extends AbstractFilter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("=======> DenyFilter doFilter");
        log.info("=======> DenyFilter doFilter");
        chain.doFilter(request, response);
    }

    @Override
    public SecurityFilterEnum getAuthEnum() {
        return SecurityFilterEnum.deny;
    }
}
