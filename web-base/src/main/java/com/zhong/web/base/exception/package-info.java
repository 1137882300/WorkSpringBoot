/**
 * @author: juzi
 * @date: 2023/8/3
 * @desc:
 */
package com.zhong.web.base.exception;

/**
 * @author juzi
 * @date 2023/8/3 下午 1:43
 * @description 异常处理
 * HandlerExceptionResolver 接口的作用是允许您定义自定义的异常处理逻辑，并返回适当的响应给客户端。
 * implements HandlerExceptionResolver：用于处理 Web 应用程序中控制器抛出的异常。
 *
 * 通过实现 HandlerExceptionResolver 接口，您可以自己处理异常，控制异常的处理方式，并返回合适的结果给用户。这比默认的异常处理机制更加灵活，可以根据具体的业务需求来自定义异常的处理。
 */