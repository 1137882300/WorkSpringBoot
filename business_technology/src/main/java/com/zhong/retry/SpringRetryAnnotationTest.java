package com.zhong.retry;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

/**
 * @author: juzi
 * @date: 2023/7/28
 * @desc: 注解式：重试
 * <p>
 * 需要重试的方法上加@Retryable
 * 在重试失败的回调方法上加@Recover
 */
@Slf4j
public class SpringRetryAnnotationTest {

    //开启注解
    //proxyTargetClass  表示使用jdk还是cglib 动态代理
    @EnableRetry
    public static class Application {
    }

    /**
     * 重试所调用方法
     */
    @Retryable(value = {RemoteAccessException.class}, maxAttempts = 4, backoff = @Backoff(delay = 2000L, multiplier = 2))
    public static boolean call(String param) {
        return SpringRetryTemplateTest.RetryDemoTask.retryTask(param);
    }


    /**
     * 达到最大重试次数,或抛出了一个没有指定进行重试的异常
     */
    @Recover
    public boolean recover(Exception e, String param) {
        log.error("达到最大重试次数,或抛出了一个没有指定进行重试的异常:", e);
        return false;
    }

    @Test
    public void retry() {
        boolean abc = SpringRetryAnnotationTest.call("abc");
        log.info("--结果是:{}--", abc);
    }
}
