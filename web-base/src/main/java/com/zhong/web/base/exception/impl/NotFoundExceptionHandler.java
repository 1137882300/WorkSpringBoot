package com.zhong.web.base.exception.impl;

import com.zhong.common.ApiResponse;
import com.zhong.common.ApiResultCode;
import com.zhong.web.base.exception.base.AbstractExceptionHandler;
import com.zhong.web.base.exception.base.IExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
@Order(IExceptionHandler.DEFAULT_ORDER)
public class NotFoundExceptionHandler extends AbstractExceptionHandler<NoHandlerFoundException> {

    @Override
    protected Object doResolveException(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, NoHandlerFoundException ex) {
        recordCode(request, ApiResultCode.NOT_FOUND.code);
        return ApiResponse.fail(ApiResultCode.NOT_FOUND);
    }

}
