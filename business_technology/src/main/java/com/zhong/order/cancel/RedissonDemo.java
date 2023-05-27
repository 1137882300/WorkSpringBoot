package com.zhong.order.cancel;


import io.netty.util.HashedWheelTimer;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Redisson他是Redis的儿子（Redis son），基于Redis实现了非常多的功能，其中最常使用的就是Redis分布式锁的实现，
 * 但是除了实现Redis分布式锁之外，它还实现了延迟队列的功能。
 * SANYOU前面的前缀都是固定的，Redisson创建的时候会拼上前缀。
 * redisson_delay_queue_timeout:SANYOU，sorted set数据类型，存放所有延迟任务，按照延迟任务的到期时间戳（提交任务时的时间戳 + 延迟时间）来排序的，所以列表的最前面的第一个元素就是整个延迟队列中最早要被执行的任务，这个概念很重要
 * redisson_delay_queue:SANYOU，list数据类型，也是存放所有的任务，但是研究下来发现好像没什么用。。
 * SANYOU，list数据类型，被称为目标队列，这个里面存放的任务都是已经到了延迟时间的，可以被消费者获取的任务，所以上面demo中的RBlockingQueue的take方法是从这个目标队列中获取到任务的
 * redisson_delay_queue_channel:SANYOU，是一个channel，用来通知客户端开启一个延迟任务
 * 任务提交的时候，Redisson会将任务放到redisson_delay_queue_timeout:SANYOU中，分数就是提交任务的时间戳+延迟时间，就是延迟任务的到期时间戳
 * Redisson客户端内部通过监听redisson_delay_queue_channel:SANYOU这个channel来提交一个延迟任务，这个延迟任务能够保证将redisson_delay_queue_timeout:SANYOU中到了延迟时间的任务从redisson_delay_queue_timeout:SANYOU中移除，存到SANYOU这个目标队列中。
 * 于是消费者就可以从SANYOU这个目标队列获取到延迟任务了。
 * 所以从这可以看出，Redisson的延迟任务的实现跟前面说的MQ的实现都是殊途同归，最开始任务放到中间的一个地方，叫做redisson_delay_queue_timeout:SANYOU，然后会开启一个类似于定时任务的一个东西，去判断这个中间地方的消息是否到了延迟时间，到了再放到最终的目标的队列供消费者消费。
 * Redisson的这种实现方式比监听Redis过期key的实现方式更加可靠，因为消息都存在list和sorted set数据类型中，所以消息很少丢。
 * 上述说的两种Redis的方案更详细的介绍，可以查看我之前写的用Redis实现延迟队列，我研究了两种方案，发现并不简单这篇文章。
 */
@Slf4j
public class RedissonDemo {


    /**
     * 这个类在创建的时候会去初始化延迟队列，创建一个RedissonClient对象，
     * 之后通过RedissonClient对象获取到RDelayedQueue和RBlockingQueue对象，传入的队列名字叫SANYOU，这个名字无所谓。
     * 当延迟队列创建之后，会开启一个延迟任务的消费线程，这个线程会一直从RBlockingQueue中通过take方法阻塞获取延迟任务。
     * 添加任务的时候是通过RDelayedQueue的offer方法添加的。
     */
    @Component
    @Slf4j
    public static class RedissonDelayQueue {

        private RDelayedQueue<String> delayQueue;
        private RBlockingQueue<String> blockingQueue;

        @PostConstruct
        public void init() {
            initDelayQueue();
            startDelayQueueConsumer();
        }

        private void initDelayQueue() {
            Config config = new Config();
            SingleServerConfig serverConfig = config.useSingleServer();
            serverConfig.setAddress("redis://localhost:6379");
            RedissonClient redissonClient = Redisson.create(config);

            blockingQueue = redissonClient.getBlockingQueue("SANYOU");
            delayQueue = redissonClient.getDelayedQueue(blockingQueue);
        }

        private void startDelayQueueConsumer() {
            new Thread(() -> {
                while (true) {
                    try {
                        String task = blockingQueue.take();
                        log.info("接收到延迟任务:{}", task);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, "SANYOU-Consumer").start();
        }

        public void offerTask(String task, long seconds) {
            log.info("添加延迟任务:{} 延迟时间:{}s", task, seconds);
            delayQueue.offer(task, seconds, TimeUnit.SECONDS);
        }

    }

    @RestController
    public static class RedissonDelayQueueController {

        @Resource
        private RedissonDelayQueue redissonDelayQueue;

        @GetMapping("/add")
        public void addTask(@RequestParam("task") String task) {
            redissonDelayQueue.offerTask(task, 5);
        }

    }

}
