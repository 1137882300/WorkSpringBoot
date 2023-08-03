package com.zhong.web.base.exception.impl;

import com.zhong.common.ApiException;
import com.zhong.common.ApiResponse;
import com.zhong.common.ApiResultCode;
import com.zhong.context.AppContext;
import com.zhong.web.base.exception.base.AbstractExceptionHandler;
import com.zhong.web.base.exception.base.IExceptionHandler;
import org.springframework.core.annotation.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;

/**
 * 接口验证异常
 */
@Order(IExceptionHandler.DEFAULT_ORDER)
public class ValidationExceptionExceptionHandler extends AbstractExceptionHandler<ValidationException> {

    @Override
    protected Object doResolveException(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, ValidationException ex) {
        if (ex.getCause() instanceof ApiException) {
            ApiException apiException = (ApiException) ex.getCause();
            return ApiResponse.of(apiException.getCode(), apiException.getMessage(), null);
        }
        if (AppContext.isProd) {
            return ApiResponse.of(ApiResultCode.INTERNAL_SERVER_ERROR, "服务繁忙", null);
        }
        return ApiResponse.fail(ex.getMessage());
    }

}
