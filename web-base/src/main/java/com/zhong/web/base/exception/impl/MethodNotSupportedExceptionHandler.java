package com.zhong.web.base.exception.impl;


import com.zhong.common.ApiResponse;
import com.zhong.web.base.exception.base.AbstractExceptionHandler;
import com.zhong.web.base.exception.base.IExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求方式错误 比如post用了get方式请求
 *
 */
@Order(IExceptionHandler.DEFAULT_ORDER)
public class MethodNotSupportedExceptionHandler extends AbstractExceptionHandler<HttpRequestMethodNotSupportedException> {


    @Override
    protected Object doResolveException(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, HttpRequestMethodNotSupportedException ex) {
        return ApiResponse.fail("请求方式错误");
    }

}
