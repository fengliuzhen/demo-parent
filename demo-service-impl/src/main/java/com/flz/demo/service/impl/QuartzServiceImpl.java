package com.flz.demo.service.impl;

import com.flz.demo.dao.QuartzDao;
import com.flz.demo.entity.JobAndTriggerEntity;
import com.flz.demo.service.QuartzService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuartzServiceImpl implements QuartzService {

    @Autowired
    private QuartzDao quartzDao;

    public PageInfo<JobAndTriggerEntity> getJobAndTriggerDetails(Integer pageNum, Integer pageSize)
    {
        PageHelper.startPage(pageNum,pageSize);
        List<JobAndTriggerEntity> list = quartzDao.getJobAndTriggerDetails();
        PageInfo<JobAndTriggerEntity> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }
}
