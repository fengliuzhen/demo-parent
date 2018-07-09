package com.flz.demo.quartz.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.flz.demo.entity.JobAndTriggerEntity;
import com.flz.demo.quartz.job.BaseJob;
import com.flz.demo.service.QuartzService;
import com.github.pagehelper.PageInfo;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/job")
public class JobController {

    @Autowired
    private QuartzService quartzService;

    //加入Qulifier注解，通过名称注入bean
    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;

    private static Logger log = LoggerFactory.getLogger(JobController.class);


    @PostMapping(value="/addjob")
    @ResponseBody
    public void addjob(@RequestParam(value="jobClassName")String jobClassName,
                       @RequestParam(value="jobGroupName")String jobGroupName,
                       @RequestParam(value="cronExpression")String cronExpression) throws Exception
    {
        addJob(jobClassName, jobGroupName, cronExpression);
    }

    public void addJob(String jobClassName, String jobGroupName, String cronExpression)throws Exception{

        // 启动调度器
        scheduler.start();

        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(jobClassName, jobGroupName).build();

        //表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName)
                .withSchedule(scheduleBuilder).build();

        try {
            Date date=scheduler.scheduleJob(jobDetail, trigger);
            System.out.println(date);

        } catch (SchedulerException e) {
            System.out.println("创建定时任务失败"+e);
            throw new Exception("创建定时任务失败");
        }
    }


    @PostMapping(value="/pausejob")
    @ResponseBody
    public void pausejob(@RequestParam(value="jobClassName")String jobClassName, @RequestParam(value="jobGroupName")String jobGroupName) throws Exception
    {
        jobPause(jobClassName, jobGroupName);
    }

    public void jobPause(String jobClassName, String jobGroupName) throws Exception
    {
        scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
    }


    @PostMapping(value="/resumejob")
    @ResponseBody
    public void resumejob(@RequestParam(value="jobClassName")String jobClassName, @RequestParam(value="jobGroupName")String jobGroupName) throws Exception
    {
        jobresume(jobClassName, jobGroupName);
    }

    public void jobresume(String jobClassName, String jobGroupName) throws Exception
    {
        scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
    }


    @PostMapping(value="/reschedulejob")
    @ResponseBody
    public void rescheduleJob(@RequestParam(value="jobClassName")String jobClassName,
                              @RequestParam(value="jobGroupName")String jobGroupName,
                              @RequestParam(value="cronExpression")String cronExpression) throws Exception
    {
        jobreschedule(jobClassName, jobGroupName, cronExpression);
    }

    public void jobreschedule(String jobClassName, String jobGroupName, String cronExpression) throws Exception
    {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            System.out.println("更新定时任务失败"+e);
            throw new Exception("更新定时任务失败");
        }
    }


    @PostMapping(value="/deletejob")
    @ResponseBody
    public void deletejob(@RequestParam(value="jobClassName")String jobClassName, @RequestParam(value="jobGroupName")String jobGroupName) throws Exception
    {
        jobdelete(jobClassName, jobGroupName);
    }

    public void jobdelete(String jobClassName, String jobGroupName) throws Exception
    {
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
    }


    @GetMapping(value="/queryjob")
    @ResponseBody
    public Map<String, Object> queryjob(@RequestParam(value="pageNum")Integer pageNum, @RequestParam(value="pageSize")Integer pageSize)
    {
        PageInfo<JobAndTriggerEntity> jobAndTrigger = quartzService.getJobAndTriggerDetails(pageNum, pageSize);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("JobAndTriggerEntity", jobAndTrigger);
        map.put("number", jobAndTrigger.getTotal());
        return map;
    }
    @RequestMapping("/list")
    public String GoodsList(Model model)
    {
        return "jobsllist";
    }

    public static BaseJob getClass(String classname) throws Exception
    {
        Class<?> class1 = Class.forName(classname);
        return (BaseJob)class1.newInstance();
    }
}
