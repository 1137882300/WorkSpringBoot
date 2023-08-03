package com.zhong.web.base.exception.impl;


import com.zhong.common.ApiException;
import com.zhong.common.ApiResponse;
import com.zhong.web.base.exception.base.AbstractExceptionHandler;
import com.zhong.web.base.exception.base.IExceptionHandler;
import org.springframework.core.annotation.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义错误
 *
 * @author ashui
 * @date 2020/11/20
 */
@Order(IExceptionHandler.DEFAULT_ORDER)
public class ApiExceptionHandler extends AbstractExceptionHandler<ApiException> {


    @Override
    protected Object doResolveException(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, ApiException ex) {
        recordCode(request, ex.getCode().code);
        if (ex.isLog()) {
           log(request, ex, "code={},message={}", ex.getCode(), ex.getMessage());
        }
        return ApiResponse.of(ex.getCode(), ex.getMessage(), ex.getObject());
    }


}
