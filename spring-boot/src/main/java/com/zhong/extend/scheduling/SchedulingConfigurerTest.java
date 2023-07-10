package com.zhong.extend.scheduling;

import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * @author: juzi
 * @date: 2023/7/10
 * @desc:
 */
public class SchedulingConfigurerTest {

    public static class test implements SchedulingConfigurer {

        @Override
        public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        }
    }

}
