package com.zhong.web.base.exception.impl;


import com.zhong.common.ApiResponse;
import com.zhong.web.base.exception.base.AbstractExceptionHandler;
import com.zhong.web.base.exception.base.IExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.MissingServletRequestParameterException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 缺少参数
 */
@Order(IExceptionHandler.DEFAULT_ORDER)
public class MissingServletRequestParameterExceptionHandler extends AbstractExceptionHandler<MissingServletRequestParameterException> {

    @Override
    protected Object doResolveException(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, MissingServletRequestParameterException ex) {
        return ApiResponse.fail("缺少参数：" + ex.getParameterName());
    }

}
