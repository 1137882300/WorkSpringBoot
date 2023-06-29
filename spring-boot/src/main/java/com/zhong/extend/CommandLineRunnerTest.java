package com.zhong.extend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author: juzi
 * @date: 2023/6/29
 * @desc: Spring 容器在启动后，会自动调用执行 run 方法。并且在整个应用生命周期内只会执行一次。
 * 使用场景：
 * 1. 数据库初始化：   当应用程序启动时，你可能需要对数据库执行一些初始化操作，如添加默认数据、创建表格等。
 * 2. 缓存预加载：    在应用程序启动时，你可能希望预加载一些数据到缓存中，以提高后续的访问性能。
 * 3. 外部资源初始化： 当应用程序启动时，你可能需要连接到其他外部资源，如消息队列、文件系统、第三方服务等。
 * 4. 执行定时任务：   有时你可能需要以定时的方式执行一些任务，例如数据清理、日志切割等。
 */
public class CommandLineRunnerTest {

    @Order(1)
    @Component
    public static class StartupRunner implements CommandLineRunner {
        private static final Logger logger = LoggerFactory.getLogger(StartupRunner.class);

        @Override
        public void run(String... args) {
            logger.info("startup runner");
        }

    }

}
