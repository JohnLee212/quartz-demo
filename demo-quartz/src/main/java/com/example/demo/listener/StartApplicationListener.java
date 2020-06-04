package com.example.demo.listener;

import com.example.demo.config.SchedulerConfig;
import com.example.demo.schedule.QuartzJob;
import com.example.demo.schedule.jobs.QuartzJob2;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by John Lee on 2020/5/28 19:33
 *
 * @author admin
 */
@Component
@Slf4j
public class StartApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private SchedulerConfig schedulerConfig;
    public static AtomicInteger count = new AtomicInteger(0);
    private static String TRIGGER_GROUP_NAME = "test_trriger";
    private static String JOB_GROUP_NAME = "test_job";

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 防止重复执行
        if (event.getApplicationContext().getParent() == null && count.incrementAndGet() <= 1) {
            initJob();
        }
    }

    /**
     * 初始化一个测试JOB任务
     */
    public void initJob() {
        Scheduler scheduler;
        try {
            scheduler = schedulerConfig.scheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey("trigger", TRIGGER_GROUP_NAME);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (null == trigger) {
                Class<? extends Job> clazz = QuartzJob.class;
                JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity("job", JOB_GROUP_NAME).build();
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");
                trigger = TriggerBuilder.newTrigger().withIdentity("trigger", TRIGGER_GROUP_NAME)
                        .withSchedule(scheduleBuilder).build();
                scheduler.scheduleJob(jobDetail, trigger);
                log.info("Quartz 创建了job:...:{}", jobDetail.getKey());
            } else {
                log.info("job已存在:{}", trigger.getKey());
            }
            scheduler.start();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }
}
