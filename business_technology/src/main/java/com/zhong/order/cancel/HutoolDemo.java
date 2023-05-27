package com.zhong.order.cancel;

import cn.hutool.cron.timingwheel.SystemTimer;
import cn.hutool.cron.timingwheel.TimerTask;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HutoolDemo {

    public static void main(String[] args) {
        SystemTimer systemTimer = new SystemTimer();
        systemTimer.start();

        log.info("提交延迟任务");
        systemTimer.addTask(new TimerTask(() -> log.info("执行延迟任务"), 5000));
    }

}
