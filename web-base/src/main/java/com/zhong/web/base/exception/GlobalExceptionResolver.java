package com.zhong.web.base.exception;

import com.zhong.web.base.exception.base.IExceptionHandler;
import com.zhong.web.base.exception.view.IViewAndModeResolver;
import lombok.Data;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常
 */
@Data
public class GlobalExceptionResolver implements HandlerExceptionResolver {

    private ExceptionHandlerManager manager;
    private IViewAndModeResolver viewAndModeResolver;

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception ex) {
        IExceptionHandler exHandler = manager.find(ex);
        if (exHandler != null) {
            Object respObj = exHandler.resolveException(httpServletRequest, httpServletResponse, handler, ex);
            if (respObj != null) {
                return viewAndModeResolver.resolve(respObj);
            }
        }
        return null;
    }


}
