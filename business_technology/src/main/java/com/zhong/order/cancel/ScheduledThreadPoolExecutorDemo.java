package com.zhong.order.cancel;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ScheduledThreadPoolExecutorDemo {
    /**
     * 由于Timer在使用上有一定的问题，所以在JDK1.5版本的时候提供了ScheduledThreadPoolExecutor，
     * 这个跟Timer的作用差不多，并且他们的方法的命名都是差不多的，但是ScheduledThreadPoolExecutor解决了单线程和异常崩溃等问题。
     */
    public static void main(String[] args) {

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2, new ThreadPoolExecutor.CallerRunsPolicy());

        log.info("提交延迟任务");
        executor.schedule(() -> log.info("执行延迟任务"), 5, TimeUnit.SECONDS);
    }

}
