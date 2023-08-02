package com.zhong.date.exception;

/**
 * @author hengyu
 * @date 2021/09/10
 * 日期格式错误异常
 */
public class WLDateException extends RuntimeException {

    public WLDateException(String msg) {
        super(msg);
    }

    public static WLDateException throwException(String msg) throws WLDateException {
        throw new WLDateException(msg);
    }
}
