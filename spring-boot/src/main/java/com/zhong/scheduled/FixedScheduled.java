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
     * <p>
     * 秒（0~59） 例如0/5表示每5秒
     * 分（0~59）
     * 时（0~23）
     * 日（0~31）的某天，需计算
     * 月（0~11）
     * 周几（ 可填1-7 或 SUN/MON/TUE/WED/THU/FRI/SAT）
     * 年
     * <p>
     * 在大部分使用cron的场景下， - * / ? 这几个常用字符就可以满足我们的需求了。
     * <p>
     * 【*】：每的意思。在不同的字段上，就代表每秒，每分，每小时等。
     * 【-】：指定值的范围。比如[1-10]，在秒字段里就是每分钟的第1到10秒，在分就是每小时的第1到10分钟，以此类推。
     * 【,】：指定某几个值。比如[2,4,5]，在秒字段里就是每分钟的第2，第4，第5秒，以此类推。
     * 【/】：指定值的起始和增加幅度。比如[3/5]，在秒字段就是每分钟的第3秒开始，每隔5秒生效一次，也就是第3秒、8秒、13秒，以此类推。
     * 【?】：仅用于【日】和【周】字段。因为在指定某日和周几的时候，这两个值实际上是冲突的，所以需要用【?】标识不生效的字段。比如【0 1 * * * ?】就代表每年每月每日每小时的1分0秒触发任务。这里的周就没有效果了。
     */

    @Scheduled(cron = "0/5 * * * * *")
    public void run(){
        LocalDate now = LocalDate.now();
        System.out.println("定时任务运行："+ now);
        log.info("定时任务运行：{}", now);
    }



}
