package com.zhong.order.cancel;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
@Slf4j
public class CustomizeDemo {
    private static final List<DelayTask> DELAY_TASK_LIST = new CopyOnWriteArrayList<>();

    /**
     * 无限轮询的意思就是开启一个线程不停的去轮询任务，当这些任务到达了延迟时间，那么就执行任务。
     */
    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                try {
                    for (DelayTask delayTask : DELAY_TASK_LIST) {
                        if (delayTask.triggerTime <= System.currentTimeMillis()) {
                            log.info("处理延迟任务:{}", delayTask.taskContent);
                            DELAY_TASK_LIST.remove(delayTask);
                        }
                    }
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (Exception ignored) {
                }
            }
        }).start();

        log.info("提交延迟任务");
        DELAY_TASK_LIST.add(new DelayTask("三友的java日记", 5L));
    }

    @Getter
    @Setter
    public static class DelayTask {

        private final String taskContent;

        private final Long triggerTime;

        public DelayTask(String taskContent, Long delayTime) {
            this.taskContent = taskContent;
            this.triggerTime = System.currentTimeMillis() + delayTime * 1000;
        }
    }
}
