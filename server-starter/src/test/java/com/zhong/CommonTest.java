package com.zhong;

import com.zhong.handler.HandlerHolder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author: juzi
 * @date: 2023/5/16
 * @desc:
 */
@Slf4j
public class CommonTest extends BaseTest{

    @Resource
    private HandlerHolder handlerHolder;


    @Test
    public void test(){
        handlerHolder.route(2).doHandler(2);
    }




}
