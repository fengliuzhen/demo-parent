package com.flz.demo.producer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Random;

@RestController
public class HelloController {

    @Autowired
    private DiscoveryClient client;

    @RequestMapping("/hello")
    public String Hello(String name) throws Exception
    {
        long startTime=System.currentTimeMillis();
        List<String> services=client.getServices();
        for(String str:services)
        {
            List<ServiceInstance> instance=client.getInstances(str);
            for(ServiceInstance item:instance)
            {
                ServiceInstance tmpItem=item;
                //服务信息
                String serviceHost=tmpItem.getHost();
                int servicePort=tmpItem.getPort();
                String serviceId=tmpItem.getServiceId();
                URI serviceURI=tmpItem.getUri();
            }
        }
        int newRand=new Random().nextInt(3000);

        Thread.sleep(newRand);
        System.out.println(name);

        long endTime=System.currentTimeMillis();

        return "producer:"+name+",阻塞时间："+newRand+",业务处理时间："+(endTime-startTime);
    }
    @RequestMapping("/hi")
    @HystrixCommand(fallbackMethod = "hiError")
    public String home(@RequestParam String name) {
        return "hi "+name+",i am from port:" +2000;
    }

    public String hiError(String name) {
        return "hi,"+name+",sorry,error!";
    }
}
