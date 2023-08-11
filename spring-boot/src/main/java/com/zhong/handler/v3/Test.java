package com.zhong.handler.v3;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: juzi
 * @date: 2023/8/11
 * @desc:
 */
@Component
public class Test {

    @Resource
    private Handler handler;


    public void test() {
        //handler, 这个handler是带有 @Primary 的那个handler

    }

}
