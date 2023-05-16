package com.zhong.handler;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @author: juzi
 * @date: 2023/5/15
 * @desc:
 */
public abstract class BaseHandler implements Handler {

    @Autowired
    private HandlerHolder handlerHolder;

    protected Integer channelCode;

    /**
     * @author juzi
     * @date 2023/5/16 上午 10:17
     * @description 每一个继承的类，都会执行这个方法
     */
    @PostConstruct
    private void init() {
        handlerHolder.putHandler(channelCode, this);
    }


    /**
     * @author juzi
     * @date 2023/5/16 上午 10:20
     * @description 各实现类处理之后 的处理
     */
    @Override
    public void doHandler(Integer code) {
        if (this.handler(true)) {
            System.out.println("BaseHandler: " + code + Boolean.TRUE);
            return;
        }
        System.out.println("BaseHandler: " + code + Boolean.FALSE);
    }


    /**
     * @author juzi
     * @date 2023/5/16 上午 10:20
     * @description 各实现类处理自己的事务
     */
    public abstract boolean handler(boolean boo);

}
