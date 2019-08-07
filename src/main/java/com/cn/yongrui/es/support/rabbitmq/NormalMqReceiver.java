/*
package com.cn.yongrui.es.support.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitConfig.QUEUE_NAME)
public class NormalMqReceiver {

    Logger log = LoggerFactory.getLogger(NormalMqReceiver.class);

    @RabbitHandler
    public void process(String message) {
        log.info(String.format("i have receive a message:%s"), message);
    }
}
*/
