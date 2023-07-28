package com.zhong.retry;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: juzi
 * @date: 2023/7/28
 * @desc: spring-retry 重试框架
 * 编程式方式
 */
@Slf4j
public class SpringRetryTemplateTest {

    /**
     * 重试间隔时间ms,默认1000ms
     */
    private final long fixedPeriodTime = 1000L;
    /**
     * 最大重试次数,默认为3
     */
    private final int maxRetryTimes = 3;
    /**
     * 表示哪些异常需要重试,key表示异常的字节码,value为true表示需要重试
     */
    private final Map<Class<? extends Throwable>, Boolean> exceptionMap = new HashMap<>();


    @Test
    public void test() {
        exceptionMap.put(RemoteAccessException.class, true);

        // 构建重试模板实例
        RetryTemplate retryTemplate = new RetryTemplate();

        // 设置重试回退操作策略，主要设置重试间隔时间
        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(fixedPeriodTime);

        // 设置重试策略，主要设置重试次数
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy(maxRetryTimes, exceptionMap);

        retryTemplate.setRetryPolicy(retryPolicy);
        retryTemplate.setBackOffPolicy(backOffPolicy);

        Boolean execute = retryTemplate.execute(
                //RetryCallback
                retryCallback -> {
                    boolean b = RetryDemoTask.retryTask("abc");
                    log.info("调用的结果:{}", b);
                    return b;
                },
                recoveryCallback -> {
                    //RecoveryCallback
                    log.info("已达到最大重试次数或抛出了不重试的异常~~~");
                    return false;
                }
        );

        log.info("执行结果:{}", execute);

    }

    static class RetryDemoTask {
        public static boolean retryTask(String value) {
            System.out.println(value);
            return false;
        }

    }
    /**
     * @author juzi
     * @date 2023/7/28 上午 10:12
     * @description
     *
     * NeverRetryPolicy： 只允许调用RetryCallback一次，不允许重试
     * AlwaysRetryPolicy： 允许无限重试，直到成功，此方式逻辑不当会导致死循环
     * SimpleRetryPolicy： 固定次数重试策略，默认重试最大次数为3次，RetryTemplate默认使用的策略
     * TimeoutRetryPolicy： 超时时间重试策略，默认超时时间为1秒，在指定的超时时间内允许重试
     * ExceptionClassifierRetryPolicy： 设置不同异常的重试策略，类似组合重试策略，区别在于这里只区分不同异常的重试
     * CircuitBreakerRetryPolicy： 有熔断功能的重试策略，需设置3个参数openTimeout、resetTimeout和delegate
     * CompositeRetryPolicy： 组合重试策略，有两种组合方式，乐观组合重试策略是指只要有一个策略允许即可以重试，悲观组合重试策略是指只要有一个策略不允许即可以重试，但不管哪种组合方式，组合中的每一个策略都会执行
     */

}
