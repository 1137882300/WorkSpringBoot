package com.zhong.order.cancel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisKeyExpiredEvent;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * 生产者在消息发送时需要到指定发送到哪个channel上，消费者订阅这个channel就能获取到消息。图中channel理解成MQ中的topic。
 * 并且在Redis中，有很多默认的channel，只不过向这些channel发送消息的生产者不是我们写的代码，而是Redis本身。
 * 这里面就有这么一个channel叫做__keyevent@<db>__:expired，db是指Redis数据库的序号。
 * 当某个Redis的key过期之后，Redis内部会发布一个事件到__keyevent@<db>__:expired这个channel上，只要监听这个事件，那么就可以获取到过期的key。
 * 所以基于监听Redis过期key实现延迟任务的原理如下：
 * 将延迟任务作为key，过期时间设置为延迟时间
 * 监听__keyevent@<db>__:expired这个channel，那么一旦延迟任务到了过期时间（延迟时间），那么就可以获取到这个任务
 *
 * Spring已经实现了监听__keyevent@*__:expired这个channel这个功能，__keyevent@*__:expired中的*代表通配符的意思，监听所有的数据库。
 */
@Slf4j
public class RedisDemo {
    /**
     * 虽然这种方式可以实现延迟任务，但是这种方式坑比较多
     * 任务存在延迟:
     * Redis过期事件的发布不是指key到了过期时间就发布，而是key到了过期时间被清除之后才会发布事件。
     * 而Redis过期key的两种清除策略，就是面试八股文常背的两种：
     * 惰性清除。当这个key过期之后，访问时，这个Key才会被清除
     * 定时清除。后台会定期检查一部分key，如果有key过期了，就会被清除
     * 所以即使key到了过期时间，Redis也不一定会发送key过期事件，这就到导致虽然延迟任务到了延迟时间也可能获取不到延迟任务。
     *
     * 丢消息太频繁:
     * Redis实现的发布订阅模式，消息是没有持久化机制，当消息发布到某个channel之后，如果没有客户端订阅这个channel，
     * .那么这个消息就丢了，并不会像MQ一样进行持久化，等有消费者订阅的时候再给消费者消费。
     * 所以说，假设服务重启期间，某个生产者或者是Redis本身发布了一条消息到某个channel，由于服务重启，没有监听这个channel，那么这个消息自然就丢了。
     *
     * 消息消费只有广播模式:
     * Redis的发布订阅模式消息消费只有广播模式一种。
     * 所谓的广播模式就是多个消费者订阅同一个channel，那么每个消费者都能消费到发布到这个channel的所有消息。
     *
     * 接收到所有key的某个事件:
     * 这个不属于Redis发布订阅模式的问题，而是Redis本身事件通知的问题。
     * 当监听了__keyevent@<db>__:expired的channel，那么所有的Redis的key只要发生了过期事件都会被通知给消费者，不管这个key是不是消费者想接收到的。
     * 所以如果你只想消费某一类消息的key，那么还得自行加一些标记，比如消息的key加个前缀，消费的时候判断一下带前缀的key就是需要消费的任务。
     *
     */
    @Configuration
    public static class RedisConfiguration {

        @Bean
        public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory) {
            RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
            redisMessageListenerContainer.setConnectionFactory(connectionFactory);
            return redisMessageListenerContainer;
        }

        @Bean
        public KeyExpirationEventMessageListener redisKeyExpirationListener(RedisMessageListenerContainer redisMessageListenerContainer) {
            return new KeyExpirationEventMessageListener(redisMessageListenerContainer);
        }

    }

    /**
     * set sanyou task
     *
     * expire sanyou 5
     */
    @Component
    public static class MyRedisKeyExpiredEventListener implements ApplicationListener<RedisKeyExpiredEvent> {

        @Override
        public void onApplicationEvent(RedisKeyExpiredEvent event) {
            byte[] body = event.getSource();
            System.out.println("获取到延迟消息：" + new String(body));
        }

    }

}
