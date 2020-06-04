package com.example.demo.schedule.jobs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by John Lee on 2020/5/28 19:35
 * Spring 集成quartz定时任务
 * @author admin
 */

@Component
@Slf4j
public class ScheduledTask {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Integer count0 = 1;
    private Integer count1 = 1;
    private Integer count2 = 1;

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() throws InterruptedException {
        log.info(String.format("thread Name %s,reportCurrentTime第%s次执行，当前时间为：%s",Thread.currentThread().getName(), count0++, dateFormat.format(new Date())));
    }

    @Scheduled(fixedDelay = 5000)
    public void reportCurrentTimeAfterSleep() throws InterruptedException {
        log.info(String.format("thread Name %s,reportCurrentTimeAfterSleep第%s次执行，当前时间为：%s",Thread.currentThread().getName(), count1++, dateFormat.format(new Date())));
    }

    @Scheduled(cron = "0 0 1 * * *")
    public void reportCurrentTimeCron() throws InterruptedException {
        log.info(String.format("thread Name %s,reportCurrentTimeCron第%s次执行，当前时间为：%s",Thread.currentThread().getName(), count2++, dateFormat.format(new Date())));
    }
}
