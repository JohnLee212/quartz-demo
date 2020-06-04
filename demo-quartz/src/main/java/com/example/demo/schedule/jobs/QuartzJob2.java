package com.example.demo.schedule.jobs;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by John Lee on 2020/5/28 19:31
 * @author admin
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Component
@Slf4j
public class QuartzJob2 extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        String taskName = context.getJobDetail().getKey().getName();
        log.info("---> Quartz job 2 {}, {} <----", new Date(), taskName);
    }
}
