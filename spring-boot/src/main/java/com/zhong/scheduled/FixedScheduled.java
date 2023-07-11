package com.zhong.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * @date 2022/7/20 16:25
 */
@Component
@EnableScheduling
@Slf4j
public class FixedScheduled {
    /**
     * Cron表达式参数分别表示：
     *
     * 秒（0~59） 例如0/5表示每5秒
     * 分（0~59）
     * 时（0~23）
     * 日（0~31）的某天，需计算
     * 月（0~11）
     * 周几（ 可填1-7 或 SUN/MON/TUE/WED/THU/FRI/SAT）
     * 年
     */
    @Scheduled(cron = "0/5 * * * * *")
    public void run(){
        LocalDate now = LocalDate.now();
        System.out.println("定时任务运行："+ now);
        log.info("定时任务运行：{}", now);
    }



}
