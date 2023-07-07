package com.zhong.extend.request;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author: juzi
 * @date: 2023/7/7
 * @desc: 用于对请求的请求体数据进行处理和修改的扩展点。
 */
public class RequestBodyAdviceAdapterTest {

    @ControllerAdvice
    static class customer extends RequestBodyAdviceAdapter {

        //该方法决定了该请求体处理器是否支持对请求的处理。您可以根据需要判断请求的类型、方法、路径等来返回一个布尔值来表示是否支持处理请求。
        @Override
        public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
            return false;
        }

        //该方法在请求体被读取之前调用，可以在此方法中对请求体数据进行修改或预处理。您可以修改请求体的内容，例如解密、解压等，然后返回修改后的内容。
        @Override
        public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
            return super.beforeBodyRead(inputMessage, parameter, targetType, converterType);
        }

        //该方法在请求体被读取之后调用，可以在此方法中对请求体的数据进行进一步处理。您可以在此处对请求体进行校验、转换等操作。
        @Override
        public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
            return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
        }

        //该方法在请求体为空时调用，可以在此方法中对空请求体进行处理，例如返回一个默认值或抛出异常。
        @Override
        public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
            return super.handleEmptyBody(body, inputMessage, parameter, targetType, converterType);
        }
    }
}
