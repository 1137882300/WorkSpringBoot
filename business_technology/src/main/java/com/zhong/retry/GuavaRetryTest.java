package com.zhong.retry;

import com.github.rholder.retry.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.remoting.RemoteAccessException;

import java.util.concurrent.TimeUnit;

/**
 * @author: juzi
 * @date: 2023/7/28
 * @desc: Guava Retry
 */
@Slf4j
public class GuavaRetryTest {


    @Test
    public void fun01() {
        // RetryerBuilder 构建重试实例 retryer,可以设置重试源且可以支持多个重试源，可以配置重试次数或重试超时时间，以及可以配置等待时间间隔
        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfExceptionOfType(RemoteAccessException.class)//设置异常重试源
                .retryIfResult(res -> !res)  //设置根据结果重试
                .withWaitStrategy(WaitStrategies.fixedWait(3, TimeUnit.SECONDS)) //设置等待间隔时间
                .withStopStrategy(StopStrategies.stopAfterAttempt(3)) //设置最大重试次数
                .withRetryListener(new RetryListener() {
                    //当发生重试之后，假如我们需要做一些额外的处理动作
                    @Override
                    public <V> void onRetry(Attempt<V> attempt) {
                        log.info("记录错误日志");
                    }
                })
                .build();

        try {
            retryer.call(() -> SpringRetryTemplateTest.RetryDemoTask.retryTask("abc"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @author juzi
     * @date 2023/7/28 上午 10:43
     * @description
     *
     * retryIfException： retryIfException，抛出 runtime 异常、checked 异常时都会重试，但是抛出 error 不会重试。
     * retryIfRuntimeException： retryIfRuntimeException 只会在抛 runtime 异常的时候才重试，checked 异常和error 都不重试。
     * retryIfExceptionOfType： retryIfExceptionOfType 允许我们只在发生特定异常的时候才重试，比如NullPointerException 和 IllegalStateException 都属于 runtime 异常，也包括自定义的error。
     *
     * RetryListener： 当发生重试之后，假如我们需要做一些额外的处理动作，比如log一下异常，那么可以使用RetryListener。每次重试之后，guava-retrying 会自动回调我们注册的监听。可以注册多个RetryListener，会按照注册顺序依次调用。
     */

}