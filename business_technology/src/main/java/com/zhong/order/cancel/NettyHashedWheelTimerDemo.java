package com.zhong.order.cancel;

import io.netty.util.HashedWheelTimer;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
@Slf4j
public class NettyHashedWheelTimerDemo {
    /**
     * 如图，时间轮会被分成很多格子（上述demo中的8就代表了8个格子），一个格子代表一段时间（上述demo中的100就代表一个格子是100ms），所以上述demo中，每800ms会走一圈。
     *
     * 当任务提交的之后，会根据任务的到期时间进行hash取模，计算出这个任务的执行时间所在具体的格子，然后添加到这个格子中，通过如果这个格子有多个任务，会用链表来保存。所以这个任务的添加有点像HashMap储存元素的原理。
     *
     * HashedWheelTimer内部会开启一个线程，轮询每个格子，找到到了延迟时间的任务，然后执行。
     *
     * 由于HashedWheelTimer也是单线程来处理任务，所以跟Timer一样，长时间运行的任务会导致其他任务的延时处理。
     *
     * 前面Redisson中提到的客户端延迟任务就是基于Netty的HashedWheelTimer实现的。
     */
    public static void main(String[] args) {
        HashedWheelTimer timer = new HashedWheelTimer(100, TimeUnit.MILLISECONDS, 8);
        timer.start();

        log.info("提交延迟任务");
        timer.newTimeout(timeout -> log.info("执行延迟任务"), 5, TimeUnit.SECONDS);
    }
}
