package com.zhong.web.base.exception.impl;


import com.zhong.common.ApiResponse;
import com.zhong.common.ApiResultCode;
import com.zhong.context.AppContext;
import com.zhong.web.base.exception.base.AbstractExceptionHandler;
import com.zhong.web.base.exception.base.IExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 参数输入，json序列化对象异常
 *
 * @author ashui
 * @date 2020/11/20
 */
@Order(IExceptionHandler.DEFAULT_ORDER)
public class HttpMessageNotReadableExceptionHandler extends AbstractExceptionHandler<HttpMessageNotReadableException> {


    @Override
    protected Object doResolveException(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, HttpMessageNotReadableException ex) {
        if (AppContext.isProd) {
            return ApiResponse.of(ApiResultCode.INTERNAL_SERVER_ERROR, "服务繁忙", null);
        }
        return ApiResponse.fail("参数输入错误 ：" + ex.getMessage());
    }

}
