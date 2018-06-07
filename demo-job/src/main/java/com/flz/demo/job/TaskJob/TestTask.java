package com.flz.demo.job.TaskJob;

import com.flz.demo.common.DateUtils;
import com.flz.demo.common.RabbitMQUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TestTask {

    @Autowired
    private RabbitMQUtils rabbitMQUtils;

    @Scheduled(cron="0 0/1 * * * *")
    public void showCurrenTime()
    {
        Map<String,Object> msg=new HashMap<>();
        msg.put("appKey","23l2k3");
        msg.put("isSuccess",true);
        msg.put("count",2);
        rabbitMQUtils.setVirtualHost("flz");
        rabbitMQUtils.sendMsg("flz.demo.exchange","",msg);
        System.out.println(DateUtils.getNowTime());
    }
}
