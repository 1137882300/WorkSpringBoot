package com.zhong.net;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * @date 2022/6/30 15:41
 */
@Slf4j
public class RetryUtil {

    //重试的核心
    public interface Supplier {
        void execute();
    }

    public interface Function<T, R> {
        R execute(T t);
    }


    //重试
    public static void retry(Supplier supplier, Integer maxTimes) throws Exception {
        if (Objects.isNull(maxTimes) || maxTimes == 0) {
            maxTimes = 1;
        }
        int times = 1;
        while (times <= maxTimes) {
            try {
                //执行
                supplier.execute();
                //成功直接返回
                return;
            } catch (Throwable throwable) {
                times++;
            }
        }
        throw new Exception("执行失败并超过最大重试次数：" + maxTimes);
    }

    //    有返回值

    /**
     * 在遇到异常时尝试重试
     *
     * @param retryLimit    重试次数
     * @param retryCallable 重试回调
     * @param <V>           泛型
     * @return V 结果
     */
    public static <V extends ResultCheck> V retryOnException(int retryLimit,
                                                             Callable<V> retryCallable) {
        V v = null;
        for (int i = 0; i < retryLimit; i++) {
            try {
                v = retryCallable.call();
            } catch (Exception e) {
                log.warn("retry on " + (i + 1) + " times v = " + (v == null ? null : v.getJson()), e);
            }
            if (null != v && v.matching()) break;
            log.error("retry on " + (i + 1) + " times but not matching v = " + (v == null ? null : v.getJson()));
        }
        return v;
    }


    /**
     * 在遇到异常时尝试重试
     *
     * @param retryLimit    重试次数
     * @param sleepMillis   每次重试之后休眠的时间
     * @param retryCallable 重试回调
     * @param <V>           泛型
     * @return V 结果
     * @throws java.lang.InterruptedException 线程异常
     */
    public static <V extends ResultCheck> V retryOnException(int retryLimit, long sleepMillis,
                                                             Callable<V> retryCallable) throws java.lang.InterruptedException {
        V v = null;
        for (int i = 0; i < retryLimit; i++) {
            try {
                v = retryCallable.call();
            } catch (Exception e) {
                log.warn("retry on " + (i + 1) + " times v = " + (v == null ? null : v.getJson()), e);
            }
            if (null != v && v.matching()) {
                break;
            }
            log.error("retry on " + (i + 1) + " times but not matching v = " + (v == null ? null : v.getJson()));
            if (sleepMillis > 0) {
                Thread.sleep(sleepMillis);
            }
        }
        return v;
    }

    /**
     * 回调结果检查
     */
    public interface ResultCheck {
        /**
         * 是否匹配
         *
         * @return 匹配结果
         */
        boolean matching();

        /**
         * 获取 JSON
         *
         * @return json
         */
        String getJson();
    }

//    public static R retry(Function<T, R> function, Integer maxTimes, T t) throws Exception {
//        if (Objects.isNull(maxTimes) || maxTimes == 0) {
//            maxTimes = 1;
//        }
//        int times = 1;
//        while (times <= maxTimes) {
//            try {
//                //执行
//                return function.execute(t);
//            } catch (Throwable throwable) {
//                times++;
//            }
//        }
//        throw new Exception("执行失败并超过最大重试次数：" + maxTimes);
//    }

}
