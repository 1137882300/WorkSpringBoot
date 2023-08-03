package com.zhong.web.base.exception.base;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * @author juzi
 * @date 2023/8/3 下午 2:04
 * @description 异常日志
 */

public interface IExceptionLog {

    String QUERY = "query";

    void log(HttpServletRequest request, Exception ex, String tag, Object... arg);

    @Slf4j//只能放在类上
    class DefaultExceptionLog implements IExceptionLog {

        @Override
        public void log(HttpServletRequest request, Exception ex, String tag, Object... arg) {
            log.error("{} path={},method={},port={},query={}," + tag,
                    ex.getClass().getSimpleName(),
                    request.getServletPath(),
                    request.getMethod(),
                    request.getLocalPort(),
                    request.getAttribute("query"),
                    arg);
        }
    }


}
