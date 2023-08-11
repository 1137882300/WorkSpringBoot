package com.zhong.handler.v1.impl;

import com.zhong.handler.v1.BaseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: juzi
 * @date: 2023/5/16
 * @desc:
 */
@Slf4j
@Service
public class HandlerTwoImpl extends BaseHandler {


    public HandlerTwoImpl() {
        super.channelCode = 2;
    }


    @Override
    public boolean handler(boolean boo) {
        if (boo) {
            System.out.println("HandlerTwoImpl: ture");
            return true;
        }
        System.out.println("HandlerTwoImpl: false");
        return false;
    }

    @Override
    public void recall(Void v) {

    }
}
