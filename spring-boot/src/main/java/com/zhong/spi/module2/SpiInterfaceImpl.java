package com.zhong.spi.module2;

import com.zhong.spi.module1.SpiInterface;

/**
 * @date 2022/8/10 20:38
 */
public class SpiInterfaceImpl implements SpiInterface {

    @Override
    public void say() {
        System.out.println("我是模块2的spi");
    }
}
