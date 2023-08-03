package com.zhong.web.base.exception;

import com.zhong.common.ApiResponse;
import com.zhong.web.base.exception.view.IViewAndModeResolver;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 没捕获异常处理
 * 实现 ErrorViewResolver 接口的作用是允许开发人员自定义错误视图解析器
 * 通过实现 ErrorViewResolver 接口，你可以在应用程序中定义自己的错误视图解析器，并根据不同的错误情况返回不同的错误页面或响应。
 */
public class NotFoundViewResolver implements ErrorViewResolver {

    private final IViewAndModeResolver viewAndModeResolver;

    public NotFoundViewResolver(IViewAndModeResolver viewAndModeResolver) {
        this.viewAndModeResolver = viewAndModeResolver;
    }

    /**
     * @author juzi
     * @date 2023/8/3 下午 2:56
     * @description 该方法接收一个 HttpServletRequest 对象和一个异常对象，并返回一个 ModelAndView 对象或一个 View 对象，用于表示错误视图。
     */
    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        return viewAndModeResolver.resolve(ApiResponse.of(status.value(), status.getReasonPhrase()));
    }
}
