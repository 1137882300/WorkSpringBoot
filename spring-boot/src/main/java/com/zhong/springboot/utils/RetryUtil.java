package com.zhong.springboot.utils;

import java.util.Objects;

/**
 * @date 2022/6/30 15:41
 */
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
