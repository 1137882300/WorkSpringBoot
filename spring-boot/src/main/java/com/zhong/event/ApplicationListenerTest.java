package com.zhong.event;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author: juzi
 * @date: 2023/8/14
 * @desc: 实现ApplicationListener接口来监听应用程序事件并进行埋点操作
 * 1. ApplicationReadyEvent：表示应用程序已经准备好接收请求并开始提供服务。
 * 2. ContextRefreshedEvent：表示应用程序的ApplicationContext已经初始化或刷新完成。
 * 3. ApplicationStartedEvent：表示应用程序已经开始启动，并且在ContextRefreshedEvent之前触发。
 * 4. ApplicationContextInitializedEvent：表示ApplicationContext正在初始化。
 * 5. ApplicationEnvironmentPreparedEvent：表示应用程序的环境已经准备好，但ApplicationContext还未创建。
 * 6. ApplicationFailedEvent：表示应用程序启动失败。
 * 7. ApplicationPreparedEvent：表示应用程序已经准备好，但在开始运行之前。
 * 8. ApplicationStartingEvent：表示应用程序即将启动。
 * 9. EventPublishingRunListener：表示运行SpringApplication时的事件发布器。
 * 10. SpringApplicationEvent：作为其他事件的父类，表示SpringApplication相关的事件。
 * <p>
 * todo 执行顺序：
 * ApplicationStartingEvent：应用程序即将启动的事件。
 * ApplicationEnvironmentPreparedEvent：应用程序环境已准备好的事件。
 * ApplicationContextInitializedEvent：应用程序的 ApplicationContext 正在初始化的事件。
 * ApplicationPreparedEvent：应用程序已准备好但在刷新之前的事件。
 * ContextRefreshedEvent：应用程序的 ApplicationContext 已经初始化或刷新完成的事件。
 * ApplicationStartedEvent：应用程序已经启动的事件。
 * ApplicationReadyEvent：应用程序已准备好接收请求并开始提供服务的事件。
 * ApplicationFailedEvent：应用程序启动失败的事件。
 * <p>
 *
 * 区别：
 * ApplicationStartedEvent表示应用程序已经开始启动，但此时尚未完成初始化和刷新的过程。
 * ApplicationReadyEvent表示应用程序已准备好接收请求并开始提供服务，表示应用程序已经完全启动，包括初始化和刷新过程。
 */
public class ApplicationListenerTest {


    @Component
    public static class MyApplicationListener implements ApplicationListener<ApplicationEvent> {

        @Override
        public void onApplicationEvent(ApplicationEvent event) {
            // 在这里编写处理应用程序事件的逻辑，可以进行埋点操作
            if (event instanceof ApplicationReadyEvent) {
                // ApplicationReadyEvent是Spring Boot中的一个应用程序事件，表示应用程序已经准备好接收请求并开始提供服务。
                //todo: 可以做些初始化操作 或其他 埋点操作

            } else if (event instanceof ContextRefreshedEvent) {
                // 处理应用程序上下文刷新事件的埋点逻辑
            } else if (event instanceof ApplicationStartedEvent) {
                // 处理应用程序启动事件的埋点逻辑
            }
        }

    }

}
