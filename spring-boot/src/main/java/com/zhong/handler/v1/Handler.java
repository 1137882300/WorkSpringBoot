package com.zhong.handler.v1;

/**
 * @author: juzi
 * @date: 2023/5/15
 * @desc:
 */
public interface Handler {

    /**
     * 处理器
     */
    void doHandler(Integer code);

    /**
     * 撤回消息
     */
    void recall(Void v);

}
