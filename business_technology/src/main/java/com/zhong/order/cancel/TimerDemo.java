package com.zhong.order.cancel;

import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;

@Slf4j
public class TimerDemo {
    /**
     * 不推荐使用的，主要是有以下几点原因：
     *
     * Timer使用单线程来处理任务，长时间运行的任务会导致其他任务的延时处理
     * Timer没有对运行时异常进行处理，一旦某个任务触发运行时异常，会导致整个Timer崩溃，不安全
     */
    public static void main(String[] args) {
        Timer timer = new Timer();

        log.info("提交延迟任务");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("执行延迟任务");
            }
        }, 5000);
    }

}
