package com.example.demo.service.impl;


import com.example.demo.dao.JobAndTriggerDao;
import com.example.demo.domain.JobAndTrigger;
import com.example.demo.service.IJobAndTriggerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author admin
 */
@Service
public class JobAndTriggerImpl implements IJobAndTriggerService {

    @Resource
    private JobAndTriggerDao jobAndTriggerDao;

    @Override
    public PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<JobAndTrigger> list = jobAndTriggerDao.getJobAndTriggerDetails();
        PageInfo<JobAndTrigger> page = new PageInfo<>(list);
        return page;
    }

}