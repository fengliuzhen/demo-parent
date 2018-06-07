package com.flz.demo.common;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQUtils {

    @Autowired
    private RabbitTemplate MyRabbitTemplate;

    @Bean
    public ConnectionFactory connectionFactory()
    {
        String mqHost="localhost";
        int mqPort=5672;
        String mqUserName="admin";
        String mqPassWord="123456";
        String mqVirtualHost="flz";

        CachingConnectionFactory cachingConnectionFactory=new CachingConnectionFactory(mqHost,mqPort);
        cachingConnectionFactory.setUsername(mqUserName);
        cachingConnectionFactory.setPassword(mqPassWord);
        cachingConnectionFactory.setVirtualHost(mqVirtualHost);
        cachingConnectionFactory.setPublisherConfirms(true);

        return cachingConnectionFactory;
    }
    @Bean("MyRabbitTemplate")
    public RabbitTemplate rabbitTemplate()
    {
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory());
        return rabbitTemplate;
    }

    public void sendMsg(String exchange,String routingKey,Object object)
    {
        MyRabbitTemplate.convertAndSend(exchange,routingKey,object);
    }
    public void sendMsg(String routingKey,Object object)
    {
        MyRabbitTemplate.convertAndSend(routingKey,object);
    }
}
