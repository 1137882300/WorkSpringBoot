package com.zhong.web.base.exception.impl;

import com.zhong.common.ApiResponse;
import com.zhong.web.base.exception.base.AbstractExceptionHandler;
import com.zhong.web.base.exception.base.IExceptionHandler;
import org.springframework.core.annotation.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * 接口验证异常
 *
 * @author ashui
 * @date 2020/11/20
 */
@Order(IExceptionHandler.DEFAULT_ORDER)
public class ConstraintViolationExceptionHandler extends AbstractExceptionHandler<ConstraintViolationException> {

    @Override
    protected Object doResolveException(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, ConstraintViolationException ex) {
        if (ex.getConstraintViolations() != null) {
            return ApiResponse.fail(ex.getConstraintViolations()
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(",")));
        }
        return ApiResponse.fail();
    }

}
