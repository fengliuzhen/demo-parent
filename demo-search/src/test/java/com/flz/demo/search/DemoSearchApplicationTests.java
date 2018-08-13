package com.flz.demo.search;

import com.flz.demo.search.dao.OperationDao;
import com.flz.demo.search.entity.OperationLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoSearchApplication.class)
public class DemoSearchApplicationTests {

    @Autowired
    private OperationDao operationDao;

    @Test
    public void contextLoads() {
        OperationLog log=new OperationLog();
        log.setOperationId(1);
        log.setModuleId(6);
        log.setOperContent("这里是测试的索引内容");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            log.setOperTime(format.parse("2018-08-07 15:26:39"));
        }
        catch (Exception ex)
        {

        }
        log.setMark("测试索引");
        operationDao.save(log);
    }

}
