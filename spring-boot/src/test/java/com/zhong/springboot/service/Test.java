package com.zhong.springboot.service;

import com.zhong.springboot.utils.RetryUtil;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @date 2022/7/1 10:11
 */
@SpringBootTest
public class Test {

    @org.junit.Test
    public void test() {
        try {
            RetryUtil.retry(() -> {
                System.out.println("ss");
            }, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void retry() throws Exception {
        RetryUtil.retry(() -> {
        int k = 8 / 0;
        }, 2);

        RetryUtil.retry(() -> {

        }, 2);

    }

    @org.junit.Test
    public void test2() throws Exception {

    }

}
