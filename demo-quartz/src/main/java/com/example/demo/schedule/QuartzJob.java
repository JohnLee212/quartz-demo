package com.example.demo.schedule;

import com.example.demo.domain.JobAndTrigger;
import com.example.demo.service.IJobAndTriggerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by John Lee on 2020/5/28 19:29
 *  * PersistJobDataAfterExecution 持久化
 *  * DisallowConcurrentExecution禁止并发执行(Quartz不要并发地执行同一个job定义（这里指一个job类的多个实例）)
 * @author admin
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Component
@Slf4j
public class QuartzJob extends QuartzJobBean {

    @Resource
    private IJobAndTriggerService iJobAndTriggerService;

    @Override
    protected void executeInternal(JobExecutionContext context) {
        String taskName = context.getJobDetail().getKey().getName();
        PageInfo<JobAndTrigger> jobAndTriggerDetails = iJobAndTriggerService.getJobAndTriggerDetails(0, 10);
        log.info("---> Quartz job {}, {}, jobAndTriggerDetails size {} <---- ", new Date(),
                taskName,jobAndTriggerDetails.getTotal());
    }
}
