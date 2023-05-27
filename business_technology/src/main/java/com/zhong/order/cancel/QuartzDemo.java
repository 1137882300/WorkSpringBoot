package com.zhong.order.cancel;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

@Slf4j
public class QuartzDemo implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        JobDetail jobDetail = context.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        log.info("获取到延迟任务:{}", jobDataMap.get("delayTask"));
    }


    public static void main(String[] args) throws SchedulerException {
        // 1.创建Scheduler的工厂
        SchedulerFactory sf = new StdSchedulerFactory();
        // 2.从工厂中获取调度器实例
        Scheduler scheduler = sf.getScheduler();

        // 6.启动 调度器
        scheduler.start();

        // 3.创建JobDetail，Job类型就是上面说的SanYouJob
        JobDetail jb = JobBuilder.newJob(QuartzDemo.class)
                .usingJobData("delayTask", "这是一个延迟任务")
                .build();

        // 4.创建Trigger
        Trigger t = TriggerBuilder.newTrigger()
                //任务的触发时间就是延迟任务到的延迟时间
                .startAt(DateUtil.offsetSecond(new Date(), 5))
                .build();

        // 5.注册任务和定时器
        log.info("提交延迟任务");
        scheduler.scheduleJob(jb, t);
    }
}
