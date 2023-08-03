package com.zhong.web.base.exception.impl;


import com.zhong.common.ApiResponse;
import com.zhong.web.base.exception.base.AbstractExceptionHandler;
import com.zhong.web.base.exception.base.IExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 版本号错误
 *
 * @author ashui
 * @date 2020/11/20
 */
@Order(IExceptionHandler.DEFAULT_ORDER)
public class HttpMediaTypeNotAcceptableExceptionHandler extends AbstractExceptionHandler<HttpMediaTypeNotAcceptableException> {

    @Override
    protected Object doResolveException(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, HttpMediaTypeNotAcceptableException ex) {
        return ApiResponse.fail("请求错误");
    }

}
