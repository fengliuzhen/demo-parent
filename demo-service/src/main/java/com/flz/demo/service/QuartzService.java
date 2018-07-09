package com.flz.demo.service;

import com.flz.demo.entity.JobAndTriggerEntity;
import com.github.pagehelper.PageInfo;

public interface QuartzService {
    PageInfo<JobAndTriggerEntity> getJobAndTriggerDetails(Integer pageNum, Integer pageSize);
}
