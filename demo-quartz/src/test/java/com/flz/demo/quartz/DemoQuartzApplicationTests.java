package com.flz.demo.quartz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoQuartzApplicationTests {

    @Test
    public void contextLoads() {
        //抽奖人
        List<String> personList=new ArrayList<>();
        personList.add("cat");
        personList.add("tom");
        personList.add("json");
        personList.add("object");
        personList.add("map");
        //奖品
        String[] prizeList={"phone","computer","book"};
        Random random=new Random();
        for(int i=0;i<prizeList.length;i++)
        {
            //随机数
            int randomIndex=random.nextInt(personList.size());
            System.out.println(String.format("%s 获取奖品：%s",personList.get(randomIndex),prizeList[i]));
            //移除当前中奖人
            personList.remove(randomIndex);
        }
        for(String str:personList)
        {
            System.out.println(String.format("%s 未中奖",str));
        }
    }
}
