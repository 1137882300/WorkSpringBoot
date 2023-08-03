package com.zhong.web.base.exception.impl;


import com.zhong.common.ApiResponse;
import com.zhong.web.base.exception.base.AbstractExceptionHandler;
import com.zhong.web.base.exception.base.IExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;

/**
 * 接口验证异常
 *
 */
@Order(IExceptionHandler.DEFAULT_ORDER)
public class ValidationBindExceptionHandler extends AbstractExceptionHandler<BindException> {


    @Override
    protected Object doResolveException(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, BindException ex) {
        ex.getAllErrors();
        return ApiResponse.fail(
                ex.getAllErrors()
                        .stream()
                        .map(ObjectError::getDefaultMessage)
                        .collect(Collectors.joining(","))
        );
    }

}
