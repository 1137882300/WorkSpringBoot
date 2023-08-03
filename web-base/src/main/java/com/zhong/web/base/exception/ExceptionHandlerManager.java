package com.zhong.web.base.exception;


import com.zhong.web.base.exception.base.AbstractExceptionHandler;
import com.zhong.web.base.exception.base.IExceptionHandler;
import com.zhong.web.base.exception.base.IExceptionLog;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 异常管理器
 */
@SuppressWarnings({"rawtypes"})
public class ExceptionHandlerManager implements InitializingBean, ApplicationContextAware {

    private ApplicationContext applicationContext;
    private final Map<String, IExceptionHandler> keyCache = new ConcurrentHashMap<>(16);
    private IExceptionHandler<?> defaultHandler;
    private final IExceptionLog exceptionLog;

    public ExceptionHandlerManager(IExceptionLog exceptionLog) {
        this.exceptionLog = exceptionLog;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() {
        loadHandler();
    }


    private void loadHandler() {
        List<IExceptionHandler> handlers = loadExceptionHandler();
        if (handlers.size() > 0) {
            for (IExceptionHandler<?> handler : handlers) {
                Type actualTypeArgument = ((ParameterizedType) handler.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                String typeName = actualTypeArgument.getTypeName();

                if (keyCache.containsKey(typeName)) {
                    continue;
                }
                keyCache.put(typeName, handler);

                if (typeName.equals("java.lang.Exception")) {
                    defaultHandler = handler;
                }
            }
        }

        for (IExceptionHandler<?> handler : keyCache.values()) {
            if (handler instanceof AbstractExceptionHandler) {
                AbstractExceptionHandler<?> exceptionHandler = (AbstractExceptionHandler<?>) handler;
                exceptionHandler.setExceptionLog(exceptionLog);
            }
        }
    }
    private List<IExceptionHandler> loadExceptionHandler() {
        List<IExceptionHandler> handlers = SpringFactoriesLoader.loadFactories(IExceptionHandler.class, null);
        Map<String, IExceptionHandler> beans = this.applicationContext.getBeansOfType(IExceptionHandler.class);
        if (beans.size() > 0) {
            List<IExceptionHandler> collect = new ArrayList<>(beans.values());
            handlers.addAll(collect);
            AnnotationAwareOrderComparator.sort(handlers);
        }
        return handlers;
    }


    public IExceptionHandler find(Exception ex) {
        String typeName = ex.getClass().getTypeName();
        IExceptionHandler handler = keyCache.get(typeName);
        if (handler == null) {
            return defaultHandler;
        }
        return handler;
    }


}
