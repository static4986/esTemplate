package com.cn.yongrui.es.support.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue queue(){
        return new Queue(QUEUE_NAME);
    }

    public final static String QUEUE_NAME="q_hello";
}
