package com.zhong.event;

import com.zhong.spring.SpringUtil;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author: juzi
 * @date: 2023/8/7
 * @desc: 自定义的事件
 */
public class ApplicationEventTest extends ApplicationEvent {

    @Getter
    private final Integer id;

    public ApplicationEventTest(Object source, Integer id) {
        super(source);
        this.id = id;
    }

    /**
     * @author juzi
     * @date 2023/8/14 上午 11:25
     * @description 监听器
     */
    public interface Listener {

        @Async
        @TransactionalEventListener(fallbackExecution = true, classes = {
                Payed.class,
                Created.class,
                Ticketed.class,
                Refunded.class,
                Cancelled.class
        })
        default void onAllEvent(ApplicationEventTest event) {
        }

        //@TransactionalEventListener 默认只处理在事务提交成功后触发的事件。
        //通过 fallbackExecution 属性可以指定是否在事务失败时执行，为 true 则在事务回滚后依然执行
        //可以通过 phase 属性设置执行的阶段，默认为 TransactionPhase.AFTER_COMMIT，表示事务提交后立即执行，还可以选择在事务提交前进行处理。
        @Async
        @TransactionalEventListener(fallbackExecution = true, classes = {Created.class,})
        default void onCreated(Created event) {
        }

        @Async
        @TransactionalEventListener(fallbackExecution = true, classes = {Payed.class,})
        default void onPayed(Payed event) {
        }

        @Async
        @TransactionalEventListener(fallbackExecution = true, classes = {Cancelled.class,})
        default void onCancelled(Cancelled event) {
        }

        @Async
        @TransactionalEventListener(fallbackExecution = true, classes = {Refunded.class,})
        default void onRefunded(Refunded event) {
        }

        @Async
        @TransactionalEventListener(fallbackExecution = true, classes = {Ticketed.class})
        default void onTicketed(Ticketed event) {
        }

    }

    /**
     * @author juzi
     * @date 2023/8/14 上午 11:25
     * @description 事件类型
     */
    public static class Created extends ApplicationEventTest {
        public Created(Object source, Integer ticketOrderId) {
            super(source, ticketOrderId);
        }
    }

    public static class Cancelled extends ApplicationEventTest {
        public Cancelled(Object source, Integer ticketOrderId) {
            super(source, ticketOrderId);
        }
    }

    public static class Refunded extends ApplicationEventTest {
        @Getter
        private final Integer ticketRefundId;

        public Refunded(Object source, Integer ticketOrderId, Integer ticketRefundId) {
            super(source, ticketOrderId);
            this.ticketRefundId = ticketRefundId;
        }
    }

    public static class Ticketed extends ApplicationEventTest {
        public Ticketed(Object source, Integer ticketOrderId) {
            super(source, ticketOrderId);
        }
    }

    public static class Payed extends ApplicationEventTest {
        public Payed(Object source, Integer ticketOrderId) {
            super(source, ticketOrderId);
        }
    }


    /**
     * @author juzi
     * @date 2023/8/14 上午 11:25
     * @description 发送器
     */
    public static class Sender {

        public static void sendCreated(Object source, Integer ticketOrderId) {
            SpringUtil.getApplicationContext().publishEvent(new Created(source, ticketOrderId));
        }

        public static void sendPayed(Object source, Integer ticketOrderId) {
            SpringUtil.getApplicationContext().publishEvent(new Payed(source, ticketOrderId));
        }

        public static void sendCancelled(Object source, Integer ticketOrderId) {
            SpringUtil.getApplicationContext().publishEvent(new Cancelled(source, ticketOrderId));
        }

        public static void sendRefunded(Object source, Integer ticketOrderId, Integer ticketRefundId) {
            SpringUtil.getApplicationContext().publishEvent(new Refunded(source, ticketOrderId, ticketRefundId));
        }

        public static void sendTicketed(Object source, Integer ticketOrderId) {
            SpringUtil.getApplicationContext().publishEvent(new Ticketed(source, ticketOrderId));
        }
    }

    /**
     * @author juzi
     * @date 2023/8/14 上午 11:32
     * @description 使用:发送事件
     */
    public static class Send {
        public void test() {
            ApplicationEventTest.Sender.sendCreated(this, 100);
        }
    }


    /**
     * @author juzi
     * @date 2023/8/14 上午 11:27
     * @description 使用:接收到事件
     */
    public static class Receive implements ApplicationEventTest.Listener {
        @Override
        public void onAllEvent(ApplicationEventTest event) {
            //接收到事件的处理
        }

        @Override
        public void onPayed(Payed event) {
            Listener.super.onPayed(event);
        }
    }


}
