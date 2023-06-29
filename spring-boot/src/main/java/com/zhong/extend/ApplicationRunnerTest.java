package com.zhong.extend;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author: juzi
 * @date: 2023/6/29
 * @desc: ApplicationRunner 接口和 CommandLineRunner 接口作用一样的，唯一不同的是 args 参数。CommandLineRunner 的 run 方法参数是 String… args，
 * 而 ApplicationRunner 的 run 方法参数是 ApplicationArguments args。
 */
public class ApplicationRunnerTest {

    @Order(1)
    @Component
    public static class Application implements ApplicationRunner {

        @Override
        public void run(ApplicationArguments args) {
            System.out.println("--- CommandLineRunner in Application...");
            System.out.println("run args optionNames=" + args.getOptionNames() + ", sourceArgs="
                    + Arrays.toString(args.getSourceArgs()) + ", name=" + args.getOptionValues("name"));
        }
    }

}
