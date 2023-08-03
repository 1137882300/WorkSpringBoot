package com.zhong.web.base.exception.base;


import com.zhong.common.ApiResultCode;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 错误处理基类
 *
 */
@Data
public abstract class AbstractExceptionHandler<E extends Exception> implements IExceptionHandler<E> {

    private IExceptionLog exceptionLog;

    protected void recordCode(HttpServletRequest request, int code) {
        request.setAttribute("code", code + "");
    }

    protected void log(HttpServletRequest request, Exception ex, String tag, Object... arg) {
        exceptionLog.log(request, ex, tag, arg);
    }


    @Override
    public Object resolveException(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, E ex) {
        recordCode(request, ApiResultCode.INTERNAL_SERVER_ERROR.code);
        return doResolveException(request, httpServletResponse, o, ex);
    }

    protected abstract Object doResolveException(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, E ex);

}
