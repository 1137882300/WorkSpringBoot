package com.zhong.handler.v2.impl;

import com.zhong.handler.v2.Handler;
import com.zhong.handler.v2.KeyConstants;
import org.springframework.stereotype.Service;

/**
 * @author: juzi
 * @date: 2023/8/11
 * @desc:
 */
@Service(value = KeyConstants.HandlerOne)
public class HandlerOneImpl implements Handler {
    @Override
    public void todo() {

    }
}
