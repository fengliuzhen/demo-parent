package com.flz.demo.quartz.job;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.flz.demo.common.SerializableUtils;
import com.flz.demo.entity.TestEntity;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import static java.util.stream.Collectors.toList;

public class HelloJob implements BaseJob {
    private static Logger _log = LoggerFactory.getLogger(HelloJob.class);

    public HelloJob() {

    }

    public void execute(JobExecutionContext context) throws JobExecutionException {
        _log.error("Hello Job执行时间: " + new Date());
        String str="[{\"name\":\"name0\",\"age\":3},{\"name\":\"name1\",\"age\":5},{\"name\":\"name2\",\"age\":10},{\"name\":\"name3\",\"age\":15},{\"name\":\"name4\",\"age\":20},{\"name\":\"name5\",\"age\":25},{\"name\":\"name6\",\"age\":30},{\"name\":\"name7\",\"age\":35},{\"name\":\"name8\",\"age\":40},{\"name\":\"name9\",\"age\":45}] ";
        List<TestEntity> list=SerializableUtils.strToList(str);

        int groupCount=2;
        int forTimes=(int)Math.ceil(list.size()/groupCount);

        //分割list
        for(int i=0;i<forTimes;i++){
            List<TestEntity> newList=list.stream().skip(i*groupCount).limit(groupCount).parallel().collect(toList());
        }

        //List<String> ids=list.stream().map(TestEntity::getName).collect(toList());


        String str2="{\"name\":\"name0\",\"age\":0}";
        TestEntity entity=SerializableUtils.strToObj(str2,TestEntity.class);
    }
}
