package com.zhong.async;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @desc 自定义异步任务线程池
 * 可以对异步任务的执行行为进行灵活的配置和定制
 */
@Slf4j
@EnableAsync
@Configuration
public class AsyncPoolConfig implements AsyncConfigurer {

    //用于获取异步任务的执行器。通过重写这个方法，我们可以自定义异步任务的执行方式，如线程池的配置、任务队列的设置等。默认情况下，Spring 使用 SimpleAsyncTaskExecutor 作为执行器。
    @Override
    @Bean
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(20);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("omsSync_");

        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();

        return executor;
    }

    //用于获取异步任务的异常处理器。在异步任务执行过程中，如果出现异常，这个异常处理器会捕获并进行相应的处理。可以通过重写这个方法来自定义异常处理器，例如将异常记录到日志中。默认情况下，Spring 使用 SimpleAsyncUncaughtExceptionHandler。
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncExceptionHandler();
    }

    static class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

        @SuppressWarnings("NullableProblems")
        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
            throwable.printStackTrace();
            log.error("AsyncError: {}, Method: {}, Param: {}",
                    throwable, method.getName(), JSON.toJSONString(objects));
        }
    }
}
