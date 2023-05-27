package com.zhong.order.cancel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * 整个工作流程如下:
 *
 * 消息发送的时候会将消息发送到sanyouDirectExchange这个交换机上
 * 由于sanyouDirectExchange绑定了sanyouQueue，所以消息会被路由到sanyouQueue这个队列上
 * 由于sanyouQueue没有消费者消费消息，并且又设置了5s的过期时间，所以当消息过期之后，消息就被放到绑定的sanyouDelayTaskExchange死信交换机中
 * 消息到达sanyouDelayTaskExchange交换机后，由于跟sanyouDelayTaskQueue进行了绑定，所以消息就被路由到sanyouDelayTaskQueue中，
 * 消费者就能从sanyouDelayTaskQueue中拿到消息了
 * 上面说的队列与交换机的绑定关系，就是上面的配置类所干的事。
 *
 * 其实从这个单从消息流转的角度可以看出，RabbitMQ跟RocketMQ实现有相似之处。
 *
 * 消息最开始都并没有放到最终消费者消费的队列中，而都是放到一个中间队列中，等消息到了过期时间或者说是延迟时间，消息就会被放到最终的队列供消费者消息。
 *
 * 只不过RabbitMQ需要你显示的手动指定消息所在的中间队列，而RocketMQ是在内部已经做好了这块逻辑。
 *
 * 除了基于RabbitMQ的死信队列来做，RabbitMQ官方还提供了延时插件，也可以实现延迟消息的功能，这个插件的大致原理也跟上面说的一样，
 * 延时消息会被先保存在一个中间的地方，叫做Mnesia，然后有一个定时任务去查询最近需要被投递的消息，将其投递到目标队列中。
 */
@Slf4j
public class RabbitmqDemo {

    public static class doWork {
        @Resource
        private RabbitTemplate rabbitTemplate;

        @GetMapping("/rabbitmq/add")
        public void addTask(@RequestParam("task") String task) {
            // 消息ID，需要封装到CorrelationData中
            CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
            log.info("提交延迟任务");
            // 发送消息
            rabbitTemplate.convertAndSend("sanyouDirectExchangee", "", task, correlationData);
        }
    }

    @Configuration
    public static class RabbitMQConfiguration {
        @Bean
        public DirectExchange sanyouDirectExchangee() {
            return new DirectExchange("sanyouDirectExchangee");
        }

        @Bean
        public Queue sanyouQueue() {
            return QueueBuilder
                    //指定队列名称，并持久化
                    .durable("sanyouQueue")
                    //设置队列的超时时间为5秒，也就是延迟任务的时间
                    .ttl(5000)
                    //指定死信交换机
                    .deadLetterExchange("sanyouDelayTaskExchangee").build();
        }

        @Bean
        public Binding sanyouQueueBinding() {
            return BindingBuilder.bind(sanyouQueue()).to(sanyouDirectExchangee()).with("");
        }

        @Bean
        public DirectExchange sanyouDelayTaskExchange() {
            return new DirectExchange("sanyouDelayTaskExchangee");
        }

        @Bean
        public Queue sanyouDelayTaskQueue() {
            return QueueBuilder
                    //指定队列名称，并持久化
                    .durable("sanyouDelayTaskQueue").build();
        }

        @Bean
        public Binding sanyouDelayTaskQueueBinding() {
            return BindingBuilder.bind(sanyouDelayTaskQueue()).to(sanyouDelayTaskExchange()).with("");
        }

    }
}
