package com.zhong.order.cancel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@Slf4j
public class DelayQueueDemo {

    /**
     * getDelay方法返回这个任务还剩多久时间可以执行，小于0的时候说明可以这个延迟任务到了执行的时间了。
     * compareTo这个是对任务排序的，保证最先到延迟时间的任务排到队列的头。
     */
    @Getter
    public static class SanYouTask implements Delayed {

        //        taskContent：延迟任务的具体的内容
        private final String taskContent;

        //        delayTime：延迟时间，秒为单位
        private final Long triggerTime;

        public SanYouTask(String taskContent, Long delayTime) {
            this.taskContent = taskContent;
            this.triggerTime = System.currentTimeMillis() + delayTime * 1000;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(triggerTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return this.triggerTime.compareTo(((SanYouTask) o).triggerTime);
        }

    }

    /**
     * offer方法在提交任务的时候，会通过根据compareTo的实现对任务进行排序，将最先需要被执行的任务放到队列头。
     *
     * take方法获取任务的时候，会拿到队列头部的元素，也就是队列中最早需要被执行的任务，通过getDelay返回值判断任务是否需要被立刻执行，如果需要的话，就返回任务，
     * 如果不需要就会等待这个任务到延迟时间的剩余时间，当时间到了就会将任务返回。
     */
    public static void main(String[] args) {
        DelayQueue<SanYouTask> sanYouTaskDelayQueue = new DelayQueue<>();

        new Thread(() -> {
            while (true) {
                try {
                    SanYouTask sanYouTask = sanYouTaskDelayQueue.take();
                    log.info("获取到延迟任务:{}", sanYouTask.getTaskContent());
                } catch (Exception ignored) {
                }
            }
        }).start();
        log.info("提交延迟任务");
        sanYouTaskDelayQueue.offer(new SanYouTask("三友的java日记5s", 5L));
        sanYouTaskDelayQueue.offer(new SanYouTask("三友的java日记3s", 3L));
        sanYouTaskDelayQueue.offer(new SanYouTask("三友的java日记8s", 8L));
    }

}
