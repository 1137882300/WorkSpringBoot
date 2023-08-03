package com.zhong.web.base.exception.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理器
 */
public interface IExceptionHandler<E extends Exception> {

    int DEFAULT_ORDER = 100;
    int PRIORITY_ORDER = 10;

    Object resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, E e);
}
