package com.zhong.order.cancel;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 *
 * RocketMQ这种实现方式相比于前面提到的三种更加可靠，因为前面提到的三种任务内容都是存在内存的，
 * 服务器重启任务就丢了，如果要实现任务不丢还得自己实现逻辑，但是RocketMQ消息有持久化机制，能够保证任务不丢失。
 *
 * 生产者发送延迟消息之后，RocketMQ服务端在接收到消息之后，会去根据延迟级别是否大于0来判断是否是延迟消息
 *
 * 如果不大于0，说明不是延迟消息，那就会将消息保存到指定的topic中
 * 如果大于0，说明是延迟消息，此时RocketMQ会进行一波偷梁换柱的操作，将消息的topic改成SCHEDULE_TOPIC_XXXX中，XXXX不是占位符，然后存储。
 * 在BocketMQ内部有一个延迟任务，相当于是一个定时任务，这个任务就会获取SCHEDULE_TOPIC_XXXX中的消息，判断消息是否到了延迟时间，
 * 如果到了，那么就会将消息的topic存储到原来真正的topic(拿我们的例子来说就是sanyouDelayTaskTopic)中，之后消费者就可以从真正的topic中获取到消息了。
 */
@Slf4j
public class RocketmqDemo {

    /**
     * 通过DefaultMQProducer发送延迟消息到sanyouDelayTaskTopic这个topic，延迟等级为2，也就是延迟时间为5s的意思。
     */
    @Component
    public static class RocketMQDelayTaskProducer {

        @Resource
        private DefaultMQProducer producer;

        @GetMapping("/rocketmq/add")
        public void addTask(@RequestParam("task") String task) throws Exception {
            Message msg = new Message("sanyouDelayTaskTopic", "TagA", task.getBytes(RemotingHelper.DEFAULT_CHARSET));
            msg.setDelayTimeLevel(2);
            // 发送消息并得到消息的发送结果，然后打印
            log.info("提交延迟任务");
            producer.send(msg);
        }

    }


    @Component
    @RocketMQMessageListener(consumerGroup = "sanyouConsumer", topic = "sanyouDelayTaskTopic")
    @Slf4j
    public static class SanYouDelayTaskTopicListener implements RocketMQListener<String> {

        @Override
        public void onMessage(String msg) {
            log.info("获取到延迟任务:{}", msg);
        }
    }

}
