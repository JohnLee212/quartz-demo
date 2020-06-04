package com.example.demo.dao;

import com.example.demo.domain.JobAndTrigger;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author admin
 */
@Mapper
public interface JobAndTriggerDao {
    /**
     * 查询任务列表
     * @return
     */
    List<JobAndTrigger> getJobAndTriggerDetails();
}
