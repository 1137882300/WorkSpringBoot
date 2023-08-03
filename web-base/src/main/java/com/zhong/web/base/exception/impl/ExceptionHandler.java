package com.zhong.web.base.exception.impl;


import com.zhong.common.ApiResponse;
import com.zhong.common.ApiResultCode;
import com.zhong.context.AppContext;
import com.zhong.web.base.exception.base.AbstractExceptionHandler;
import com.zhong.web.base.exception.base.IExceptionHandler;
import org.springframework.core.annotation.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 调用错误
 *
 * @author ashui
 * @date 2020/11/20
 */
@Order(IExceptionHandler.DEFAULT_ORDER)
public class ExceptionHandler extends AbstractExceptionHandler<Exception> {


    @Override
    protected Object doResolveException(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, Exception ex) {
        String error = this.convertException(ex);
        super.log(request, ex, "error={}", error);

        recordCode(request, ApiResultCode.INTERNAL_SERVER_ERROR.code);

        if (AppContext.isProd) {
            return ApiResponse.of(ApiResultCode.INTERNAL_SERVER_ERROR, "服务繁忙", null);
        }
        return ApiResponse.fail(error);
    }

    /**
     * @desc exception Trace 转字符串
     */
    public String convertException(Exception ex) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException ignored) {
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }


}
