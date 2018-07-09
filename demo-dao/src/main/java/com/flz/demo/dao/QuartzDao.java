package com.flz.demo.dao;

import com.flz.demo.entity.JobAndTriggerEntity;

import java.util.List;

public interface QuartzDao {
    List<JobAndTriggerEntity> getJobAndTriggerDetails();
}
