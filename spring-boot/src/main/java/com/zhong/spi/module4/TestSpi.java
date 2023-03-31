package com.zhong.spi.module4;

import com.zhong.spi.module1.SpiInterface;

import java.util.ServiceLoader;

/**
 * @date 2022/8/10 20:40
 */
public class TestSpi {

    public static void main(String[] args) {
        ServiceLoader<SpiInterface> serviceLoader = ServiceLoader.load(SpiInterface.class);
        for (SpiInterface o : serviceLoader) {
            o.say();
        }
    }
}
