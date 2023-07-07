package com.zhong.extend.request;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author: juzi
 * @date: 2023/7/7
 * @desc: 当控制器方法返回一个响应体时，ResponseBodyAdvice 提供了一种机制，让您能够在响应体返回给客户端之前对其进行自定义处理。
 * 返回时 增强
 */
public class ResponseBodyAdviceTest {

    @ControllerAdvice
    static class customer implements ResponseBodyAdvice<Object> {


        //该方法用于决定是否支持对给定控制器方法的响应进行处理。根据您的需求，可以根据控制器方法的返回类型、注解、路径等来判断是否支持处理。
        @Override
        public boolean supports(MethodParameter returnType, Class converterType) {
            return false;
        }


        //该方法在响应体写入之前调用，可以在此方法中对响应体数据进行修改或增强。您可以修改响应体的内容，例如加密、压缩、封装等，然后返回修改后的内容。
        @Override
        public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
            return null;
        }
    }
}
