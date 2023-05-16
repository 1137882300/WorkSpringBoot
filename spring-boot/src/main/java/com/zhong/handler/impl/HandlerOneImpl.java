package com.zhong.handler.impl;

import com.zhong.handler.BaseHandler;
import com.zhong.handler.Handler;
import com.zhong.handler.HandlerHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: juzi
 * @date: 2023/5/16
 * @desc:
 */
@Slf4j
@Service
public class HandlerOneImpl extends BaseHandler {


    public HandlerOneImpl() {
        super.channelCode = 1;
    }



    @Override
    public boolean handler(boolean boo) {
        if (boo) {
            System.out.println("HandlerOneImpl: ture");
            return true;
        }
        System.out.println("HandlerOneImpl: false");
        return false;
    }

    @Override
    public void recall(Void v) {

    }
}
