package com.zhong.handler.v3.impl;

import com.zhong.handler.v3.Handler;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author: juzi
 * @date: 2023/8/11
 * @desc: 加上 @Primary 自动注入时 就会注入这个
 */
@Primary
@Service
public class HandlerTwoImpl implements Handler {

}
