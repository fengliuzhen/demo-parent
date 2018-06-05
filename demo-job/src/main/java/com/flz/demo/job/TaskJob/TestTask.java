package com.flz.demo.job.TaskJob;

import com.flz.demo.common.DateUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestTask {

    @Scheduled(cron="0 0/1 * * * *")
    public void showCurrenTime()
    {
        System.out.println(DateUtils.getNowTime());
    }
}
