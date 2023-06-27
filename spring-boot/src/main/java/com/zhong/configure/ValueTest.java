package com.zhong.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author: juzi
 * @date: 2023/6/27
 * @desc:
 */
@Slf4j
public class ValueTest {

    /**
     * @date 2023/6/27 下午 5:12
     * @description 注意 final 和 static 定义的变量都不会起作用
     */
    @Value("${env101.var1:xxx}")
    private String var1;

    public void test() {
        log.info("配置文件属性: {}", var1);
    }
}
